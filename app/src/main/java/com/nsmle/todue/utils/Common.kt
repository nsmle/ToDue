package com.nsmle.todue.utils

import com.nsmle.todue.R
import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.enums.IconColor

/**
 * Colors Util
 */
data class ColorWithAlpha(val name: String, val color: Int, val alphaColor: Int);

fun iconColor(color: IconColor?): ColorWithAlpha {
	val iconColor = when (color) {
		IconColor.PURPLE       -> ColorWithAlpha("purple", R.color.purple, R.color.purple_alpha)
		IconColor.PURPLE_LIGHT -> ColorWithAlpha("purple-light", R.color.purple_light, R.color.purple_light_alpha)
		IconColor.BLUE         -> ColorWithAlpha("blue", R.color.blue, R.color.blue_alpha)
		IconColor.BLUE_LIGHT   -> ColorWithAlpha("blue-light", R.color.blue_light, R.color.blue_light_alpha)
		IconColor.PINK         -> ColorWithAlpha("pink", R.color.pink, R.color.pink_alpha)
		IconColor.PINK_LIGHT   -> ColorWithAlpha("pink-light", R.color.pink_light, R.color.pink_light_alpha)
		IconColor.TEAL         -> ColorWithAlpha("teal", R.color.teal, R.color.teal_alpha)
		IconColor.TEAL_LIGHT   -> ColorWithAlpha("teal-light", R.color.teal_light, R.color.teal_light_alpha)
		else                   -> ColorWithAlpha("pink", R.color.pink, R.color.pink_alpha)
	}

	return iconColor
}

/**
 * Icon Util
 */

fun iconAsset(icon: Icon?): Int {
	val iconAsset = when (icon) {
		Icon.PLUS  -> R.drawable.ic_plus
		Icon.CHART -> R.drawable.ic_chart
		else       -> R.drawable.ic_chart
	}

	return iconAsset
}