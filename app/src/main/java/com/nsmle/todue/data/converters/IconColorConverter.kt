package com.nsmle.todue.data.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.nsmle.todue.data.enums.IconColor

@ProvidedTypeConverter
class IconColorConverter {
	@TypeConverter
	fun fromIconColor(iconColor: IconColor?) = iconColor?.name

	@TypeConverter
	fun toIconColor(iconColor: String?) = iconColor?.let { enumValueOf<IconColor>(it) }
}