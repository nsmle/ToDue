package com.nsmle.todue.data.enums

import com.nsmle.todue.utils.DEFAULT_TASK_ICON

enum class Icon(val iconFilename: String) {
	WALLET("ic_wallet"),
	CHART("ic_chart"),
	GIFT("ic_gift"),
	PLANE("ic_paper_plane"),
	DATA_PIE("ic_data_pie"),
	PLUS("ic_plus"),
	MINUS("ic_minus"),
	DEFAULT(DEFAULT_TASK_ICON)
}