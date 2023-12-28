package com.nsmle.todue.data

import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.enums.IconColor
import com.nsmle.todue.data.enums.TaskStatus
import java.time.LocalDateTime
import java.util.Calendar

data class Task(
	val title: String,
	val description: String? = "",
	val icon: Icon?,
	var iconColor: IconColor?,
	val status: TaskStatus? = TaskStatus.LOW,
	val isRepeatable: Boolean? = false,
	val deadlineAt: String? = LocalDateTime.now().toString(),
	val categoryId: Int?,
	val id: Int,
) {
	val createdAt: String
		get() = LocalDateTime.now().toString()
	val updatedAt: String
		get() = Calendar.getInstance().toString()
}