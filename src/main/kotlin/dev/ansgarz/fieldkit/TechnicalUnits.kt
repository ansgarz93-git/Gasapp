package dev.ansgarz.fieldkit

/** Generic unit families commonly used in industrial field forms. */
enum class DiameterUnit { DN, INCH }
enum class PressureUnit { PN, CLASS }

data class TechnicalSelection<T : Enum<T>, V>(
    val unit: T,
    val value: V,
)

object TechnicalUnits {
    fun diameter(unit: DiameterUnit, value: String): TechnicalSelection<DiameterUnit, String> {
        require(value.isNotBlank()) { "Diameter value must not be blank" }
        return TechnicalSelection(unit, value.trim())
    }

    fun pressure(unit: PressureUnit, value: String): TechnicalSelection<PressureUnit, String> {
        require(value.isNotBlank()) { "Pressure rating must not be blank" }
        return TechnicalSelection(unit, value.trim())
    }
}
