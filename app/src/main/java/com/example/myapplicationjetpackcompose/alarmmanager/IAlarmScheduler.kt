package com.example.myapplicationjetpackcompose.alarmmanager

interface IAlarmScheduler {

    fun schedule(item: AlarmItem)

    fun cancel(item: AlarmItem)

}


