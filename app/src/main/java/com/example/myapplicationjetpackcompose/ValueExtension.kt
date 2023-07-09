package com.example.myapplicationjetpackcompose
import kotlinx.datetime.LocalDateTime
import java.util.Calendar

fun LocalDateTime?.formatToFullTimeVN(): String{
    if (this == null)
        return ""


    return this.dayOfMonth.toString().padStart(2,'0')
        .plus("/")
        .plus(this.month.value.toString().padStart(2,'0'))
        .plus("/")
        .plus(this.year.toString())
        .plus(" ")
        .plus(this.hour.toString())
        .plus(":")
        .plus(this.minute)
        .plus(":")
        .plus(this.second)


}

fun LocalDateTime?.formatToTimeVN(): String{
    if (this == null)
        return ""


    return this.hour.toString()
        .plus(":")
        .plus(this.minute)
        .plus(":")
        .plus(this.second)


}

fun LocalDateTime?.formatToHourMinuteVN(): String{
    if (this == null)
        return ""


    return this.hour.toString()
        .plus(":")
        .plus(this.minute)


}


fun LocalDateTime?.formatToDateVN(): String{
    if (this == null)
        return ""


    return this.dayOfMonth.toString().padStart(2,'0')
        .plus("/")
        .plus(this.month.value.toString().padStart(2,'0'))
        .plus("/")
        .plus(this.year.toString())


}




fun LocalDateTime?.formatToParamater(): String{
    if (this == null)
        return ""

    // yyyy-MM-dd HH:mm:ss

    return this.year.toString()
        .plus("-")
        .plus(this.month.value.toString().padStart(2,'0'))
        .plus("-")
        .plus(this.dayOfMonth.toString().padStart(2,'0'))
        .plus(" ")
        .plus(this.hour.toString())
        .plus(":")
        .plus(this.minute)
        .plus(":")
        .plus(this.second)

}

fun LocalDateTimeGetNow(): LocalDateTime {

    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH)
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    return kotlinx.datetime.LocalDateTime(mYear, mMonth, mDay, mHour, mMinute)


}

fun LocalDateTime.setHour(hour: Int ):  LocalDateTime {

        return kotlinx.datetime.LocalDateTime(this.year, this.month, this.dayOfMonth, hour, this.minute, this.second)
}

fun LocalDateTime.changeHourMinute(hour: Int , minute: Int ):  LocalDateTime {

    return kotlinx.datetime.LocalDateTime(
        this.year,
        this.month,
        this.dayOfMonth,
        hour,
        minute,
        this.second
    )
}

