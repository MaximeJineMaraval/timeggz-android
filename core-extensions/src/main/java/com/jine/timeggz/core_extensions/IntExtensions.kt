package com.jine.timeggz.core_extensions

import java.text.DecimalFormat

fun Int.fromSecondsToMinuteAndSeconds(): String {
    val minutes = (this % 3600) / 60
    val seconds = this % 60
    return "$minutes:${DecimalFormat("00").format(seconds)}"
}
