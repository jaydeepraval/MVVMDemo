package com.example.mvvmdemo.RoomUtil

import androidx.room.TypeConverter
import java.util.Date

class Convertors {

    @TypeConverter
    fun fromdateToLong(value: Date): Long{
        return value.time
    }

    @TypeConverter
    fun fromLongToDate(value: Long): Date{
        return Date(value)
    }
}