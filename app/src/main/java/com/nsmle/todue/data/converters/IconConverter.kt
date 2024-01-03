package com.nsmle.todue.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.nsmle.todue.data.enums.Icon

@ProvidedTypeConverter
class IconConverter {
	@TypeConverter
	fun fromIcon(icon: Icon?) = icon?.name

	@TypeConverter
	fun toIcon(icon: String?) = icon?.let { enumValueOf<Icon>(it) }
}