package com.nsmle.todue.utils

import android.content.Context

fun dpToPx(dp: Int, context: Context?): Int {
	if (context == null) return dp;
	val density = context.resources.displayMetrics.density
	return (dp * density).toInt()
}