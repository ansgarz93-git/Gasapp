import dev.ansgarz.fieldkit.*
import java.time.LocalDate

fun main() {
    check(TechnicalUnits.diameter(DiameterUnit.DN, " 50 ").value == "50")
    check(InspectionInterval(2, IntervalUnit.WEEKS).nextAfter(LocalDate.of(2026, 1, 1)) == LocalDate.of(2026, 1, 15))
    check(OrderedWorkflow(listOf("A", "B", "C")).move(2, 0).items == listOf("C", "A", "B"))
    check(CopyWithExclusions.copy(mapOf("model" to "X", "serial" to "Y"), setOf("serial")) == mapOf("model" to "X"))
    println("fieldkit smoke test: OK")
}
