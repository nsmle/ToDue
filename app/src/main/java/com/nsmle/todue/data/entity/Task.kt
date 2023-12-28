package com.nsmle.todue.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
	@PrimaryKey val id: Int,
	@ColumnInfo(name = "title") val title: String,
	@ColumnInfo(name = "description") val description: String?,
	@ColumnInfo(name = "icon") val icon: String?,
	@ColumnInfo(name = "icon_color") val iconColor: String?,
	@ColumnInfo(name = "status") val status: String?,
	@ColumnInfo(name = "is_epeatable") val isRepeatable: Boolean?,
)