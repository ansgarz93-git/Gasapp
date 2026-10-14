package dev.ansgarz.fieldkit

import java.time.LocalDate

enum class DueStatus {
    UPCOMING,
    DUE_SOON,
    DUE_TODAY,
    OVERDUE,
}

data class InspectionSchedule(
    val interval: InspectionInterval,
    val warningWindowDays: Int = 30,
) {
    init {
        require(warningWindowDays >= 0) { "Warning window must not be negative" }
    }

    fun dueDate(lastCompleted: LocalDate): LocalDate = interval.nextAfter(lastCompleted)

    fun status(
        lastCompleted: LocalDate,
        referenceDate: LocalDate,
    ): DueStatus {
        val due = dueDate(lastCompleted)

        return when {
            referenceDate.isAfter(due) -> DueStatus.OVERDUE
            referenceDate == due -> DueStatus.DUE_TODAY
            !referenceDate.isBefore(due.minusDays(warningWindowDays.toLong())) -> DueStatus.DUE_SOON
            else -> DueStatus.UPCOMING
        }
    }
}
