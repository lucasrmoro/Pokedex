package br.com.pokedex.local_storage.db

import androidx.room.TypeConverter
import java.util.Date

internal object TypeConverters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

}