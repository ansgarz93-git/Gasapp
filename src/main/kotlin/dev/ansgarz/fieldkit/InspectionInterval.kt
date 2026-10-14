package dev.ansgarz.fieldkit

import java.time.LocalDate

enum class IntervalUnit { DAYS, WEEKS, MONTHS, YEARS }

data class InspectionInterval(
    val amount: Int,
    val unit: IntervalUnit,
) {
    init {
        require(amount > 0) { "Interval amount must be positive" }
    }

    fun nextAfter(date: LocalDate): LocalDate = when (unit) {
        IntervalUnit.DAYS -> date.plusDays(amount.toLong())
        IntervalUnit.WEEKS -> date.plusWeeks(amount.toLong())
        IntervalUnit.MONTHS -> date.plusMonths(amount.toLong())
        IntervalUnit.YEARS -> date.plusYears(amount.toLong())
    }
}
