package com.nsmle.todue.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Update
import com.nsmle.todue.data.converters.IconConverter
import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.enums.IconColor
import java.util.Date

@Entity
data class Task(
	@PrimaryKey(autoGenerate = true) val id: Long = 0,
	@ColumnInfo(name = "title") val title: String,
	@ColumnInfo(name = "description") val description: String? = null,
	@ColumnInfo(name = "icon") val icon: Icon? = null,
	@ColumnInfo(name = "icon_color") val iconColor: IconColor? = null,
	@ColumnInfo(name = "status") val status: String? = null,
	@ColumnInfo(name = "is_repeatable") val isRepeatable: Boolean? = null,

	@ColumnInfo(name = "due_at") val dueAt: Date? = null,
	@ColumnInfo(name = "created_at") val createdAt: Date = Date(),
	@ColumnInfo(name = "updated_at") var updatedAt: Date = Date(),
) {
	@ColumnInfo(name = "is_done")
	var isDone: Boolean = false

	@Update
	fun onUpdate() {
		updatedAt = Date()
	}
}