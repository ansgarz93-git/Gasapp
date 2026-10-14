package dev.ansgarz.fieldkit

import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FieldKitTest {
    @Test
    fun `technical values are trimmed`() {
        assertEquals("80", TechnicalUnits.diameter(DiameterUnit.DN, " 80 ").value)
    }

    @Test
    fun `blank technical values are rejected`() {
        assertFailsWith<IllegalArgumentException> {
            TechnicalUnits.pressure(PressureUnit.PN, "   ")
        }
    }

    @Test
    fun `inspection interval calculates next date`() {
        val interval = InspectionInterval(6, IntervalUnit.MONTHS)
        assertEquals(LocalDate.of(2027, 4, 14), interval.nextAfter(LocalDate.of(2026, 10, 14)))
    }

    @Test
    fun `workflow can reorder items`() {
        val workflow = OrderedWorkflow(listOf("A", "B", "C")).move(0, 2)
        assertEquals(listOf("B", "C", "A"), workflow.items)
    }

    @Test
    fun `copy excludes identity fields`() {
        val source = mapOf("type" to "demo", "serial" to "S-001")
        assertEquals(mapOf("type" to "demo"), CopyWithExclusions.copy(source, setOf("serial")))
    }
}
