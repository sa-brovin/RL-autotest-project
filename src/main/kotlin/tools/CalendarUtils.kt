package tools

import java.text.SimpleDateFormat
import java.util.*


object CalendarUtils {
    fun calendarToString(cal: Calendar, pattern: String, locale: Locale): String {
        val format = SimpleDateFormat(pattern, locale)
        return format.format(cal.time)
    }

    fun stringToCalendar(date: String, pattern: String, locale: Locale = Locale.ENGLISH): Calendar {
        val format = SimpleDateFormat(pattern, locale)
        val d = format.parse(date)
        val cal = Calendar.getInstance()
        cal.time = d
        return cal
    }
}