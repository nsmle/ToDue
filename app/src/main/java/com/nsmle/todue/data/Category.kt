package com.nsmle.todue.data

data class Category(
	val title: String,
	val color: Int,
	val background: Int,
	val isCompleted: Boolean,
	val createdAt: String,
	val updatedAt: String,
	val id: Int,
) {
	val isActive
		get() = !isCompleted

	val isEmpty
		get() = title.isEmpty()
}