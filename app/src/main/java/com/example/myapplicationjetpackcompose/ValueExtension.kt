package com.example.myapplicationjetpackcompose
import kotlinx.datetime.LocalDateTime

fun LocalDateTime.formatToFullTimeVN(): String{
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

fun LocalDateTime.formatToTimeVN(): String{
    if (this == null)
        return ""


    return this.hour.toString()
        .plus(":")
        .plus(this.minute)
        .plus(":")
        .plus(this.second)


}


fun LocalDateTime.formatToDateVN(): String{
    if (this == null)
        return ""


    return this.dayOfMonth.toString().padStart(2,'0')
        .plus("/")
        .plus(this.month.value.toString().padStart(2,'0'))
        .plus("/")
        .plus(this.year.toString())


}


fun LocalDateTime.formatToParamater(): String{
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
