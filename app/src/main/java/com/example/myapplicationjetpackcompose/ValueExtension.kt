package com.example.myapplicationjetpackcompose
import android.content.Context
import android.net.Uri
import android.os.Environment
import kotlinx.datetime.LocalDateTime
import java.io.File
import java.net.URI
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
        .plus(this.hour.toString().padStart(2,'0'))
        .plus(":")
        .plus(this.minute.toString().padStart(2,'0'))


}


fun LocalDateTime?.formatToTimeDayVN(): String{
    if (this == null)
        return ""


    return this.hour.toString().padStart(2,'0')
        .plus(":")
        .plus(this.minute.toString().padStart(2,'0'))
        .plus(" ")
        .plus(this.getThuTrongTuanVN())
        .plus(", ")
        .plus(this.dayOfMonth.toString().padStart(2,'0'))
        .plus("/")
        .plus(this.month.value.toString().padStart(2,'0'))
        .plus("/")
        .plus(this.year.toString())

}


fun LocalDateTime?.formatToHourMinuteVN(): String {

    if (this == null)
        return ""


    return this.hour.toString().padStart(2,'0')
        .plus(":")
        .plus(this.minute.toString().padStart(2,'0'))


}


fun LocalDateTime?.formatToNgayThangNamVN(): String{
    if (this == null)
        return ""


    return this.dayOfMonth.toString().padStart(2,'0')
        .plus("/")
        .plus(this.month.value.toString().padStart(2,'0'))
        .plus("/")
        .plus(this.year.toString())


}

fun LocalDateTime?.formatToThuNgayThangNamVN(): String{
    if (this == null)
        return ""


    return this.getThuTrongTuanVN()
        .plus(", ")
        .plus(this.dayOfMonth.toString().padStart(2,'0'))
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
    val mMonth = mCalendar.get(Calendar.MONTH) + 1
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    return kotlinx.datetime.LocalDateTime(mYear, mMonth, mDay, mHour, mMinute)


}


fun LocalDateTime.LocalDateTimeGetDauNam(): LocalDateTime {

    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)

    return kotlinx.datetime.LocalDateTime(mYear, 1, 1, 0, 0)


}

fun LocalDateTime.LocalDateTimeGetCuoiNam(): LocalDateTime {

    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)

    return kotlinx.datetime.LocalDateTime(mYear, 12, 31, 23, 59)


}

fun LocalDateTime.LocalDateTimeGetDauThang(): LocalDateTime {

    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH) + 1

    return kotlinx.datetime.LocalDateTime(mYear, mMonth, 1, 0, 0)


}

fun LocalDateTime.LocalDateTimeGetCuoiThang(): LocalDateTime {

    val mCalendar = Calendar.getInstance()
    val mYear = mCalendar.get(Calendar.YEAR)
    val mMonth = mCalendar.get(Calendar.MONTH) + 1
    val mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    return kotlinx.datetime.LocalDateTime(mYear, mMonth, mDay, 23, 59)


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

fun LocalDateTime.changeYearMonthDay(year: Int , month: Int, day: Int):  LocalDateTime {

    return kotlinx.datetime.LocalDateTime(
        year,
        month,
        day,
        this.hour,
        this.minute,
        this.second
    )
}

fun LocalDateTime?.getThuTrongTuanVN():  String {

    if (this == null)
        return ""

    return when (this.dayOfWeek.value) {
        1 -> "Thứ hai"
        2 -> "Thứ ba"
        3 -> "Thứ tư"
        4 -> "Thứ năm"
        5 -> "Thứ sáu"
        6 -> "Thứ bảy"
        7 -> "Chủ nhật"
        else -> "Khác"
    }
}


fun CreateImageResquestCustom(context: Context, uri: Uri, child: String): File {

    val inputStream = context.contentResolver.openInputStream(uri)

    val path = Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_PICTURES
    )
    val file = File(path, child)
    file.createNewFile()

    inputStream.use { input ->
        file.outputStream().use { output ->
            input?.copyTo(output)
        }
    }

    return file
}

