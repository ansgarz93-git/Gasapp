package dev.ansgarz.fieldkit

import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class InspectionScheduleTest {
    private val schedule = InspectionSchedule(
        interval = InspectionInterval(12, IntervalUnit.MONTHS),
        warningWindowDays = 30,
    )
    private val lastCompleted = LocalDate.of(2025, 10, 14)

    @Test
    fun `date outside warning window is upcoming`() {
        assertEquals(
            DueStatus.UPCOMING,
            schedule.status(lastCompleted, LocalDate.of(2026, 9, 13)),
        )
    }

    @Test
    fun `date inside warning window is due soon`() {
        assertEquals(
            DueStatus.DUE_SOON,
            schedule.status(lastCompleted, LocalDate.of(2026, 9, 14)),
        )
    }

    @Test
    fun `due date is classified separately`() {
        assertEquals(
            DueStatus.DUE_TODAY,
            schedule.status(lastCompleted, LocalDate.of(2026, 10, 14)),
        )
    }

    @Test
    fun `date after due date is overdue`() {
        assertEquals(
            DueStatus.OVERDUE,
            schedule.status(lastCompleted, LocalDate.of(2026, 10, 15)),
        )
    }

    @Test
    fun `negative warning window is rejected`() {
        assertFailsWith<IllegalArgumentException> {
            InspectionSchedule(InspectionInterval(1, IntervalUnit.YEARS), -1)
        }
    }
}
