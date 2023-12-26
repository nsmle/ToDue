package com.nsmle.todue.data.enums

enum class TaskStatus(val statusName: String) {
	URGENT("urgent"),
	HIGHT("hight"),
	NORMAL("normal"),
	LOW("low-priority"),
	OPTIONAL("no-priority")
}