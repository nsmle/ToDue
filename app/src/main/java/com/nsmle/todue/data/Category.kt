package com.nsmle.todue.data

import java.util.Calendar

data class Category(
	val title: String,
	val color: Int,
	val background: Int,
	val isCompleted: Boolean,
	val id: Int,
) {
	val createdAt: String
		get() = Calendar.getInstance().toString()
	val updatedAt: String
		get() = Calendar.getInstance().toString()

	val isActive
		get() = !isCompleted

	val isEmpty
		get() = title.isEmpty()
}