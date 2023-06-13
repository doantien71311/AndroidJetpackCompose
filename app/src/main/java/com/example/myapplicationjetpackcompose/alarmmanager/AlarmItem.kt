package com.example.myapplicationjetpackcompose.alarmmanager

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val message: String
)
