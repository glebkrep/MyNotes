package com.example.mynotes.noteList

import java.text.SimpleDateFormat
import java.util.*

object Utils {
    public fun millisToDate(millis:Long):String{
        var calendar = Calendar.getInstance()
        calendar.timeInMillis = millis

        val PATTERN = "yyyy.MM.dd 'at' HH:mm:ss"
        val formatter = SimpleDateFormat(PATTERN)
        return formatter.format(calendar.time)
    }
}