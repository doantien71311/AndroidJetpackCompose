package com.example.myapplicationjetpackcompose.alarmmanager

import com.example.myapplicationjetpackcompose.CommonDataParamater
import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val message: String = "",
    val CommonDataParamater: CommonDataParamater = CommonDataParamater()
)
