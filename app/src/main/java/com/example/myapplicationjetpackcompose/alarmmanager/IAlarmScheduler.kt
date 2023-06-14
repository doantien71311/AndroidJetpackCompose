package com.example.myapplicationjetpackcompose.alarmmanager

interface IAlarmScheduler {

    fun scheduleManager(item: AlarmItem)

    fun cancelManager(item: AlarmItem)

}


