package com.nsmle.todue.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.Date

@ProvidedTypeConverter
class DateConverter {
	@TypeConverter
	fun toDate(value: Long?): Date? = value?.let { Date(it) }

	@TypeConverter
	fun toLong(value: Date?) = value?.time
}