package br.com.reconecta.utils;

import kotlinx.datetime.DayOfWeek

object EnumUtils {
    fun mapDayOfWeek(day: Int): DayOfWeek {
        return when (day) {
            0 -> DayOfWeek.SUNDAY
            1 -> DayOfWeek.MONDAY
            2 -> DayOfWeek.TUESDAY
            3 -> DayOfWeek.WEDNESDAY
            4 -> DayOfWeek.THURSDAY
            5 -> DayOfWeek.FRIDAY
            6 -> DayOfWeek.SATURDAY

            else -> DayOfWeek.MONDAY
        }
    }

    fun mapDayOfWeekInteger(day: DayOfWeek): Int {
        return when (day) {
            DayOfWeek.SUNDAY -> 0
            DayOfWeek.MONDAY -> 1
            DayOfWeek.TUESDAY -> 2
            DayOfWeek.WEDNESDAY -> 3
            DayOfWeek.THURSDAY -> 4
            DayOfWeek.FRIDAY -> 5
            DayOfWeek.SATURDAY -> 6

            else -> 0
        }
    }
}
