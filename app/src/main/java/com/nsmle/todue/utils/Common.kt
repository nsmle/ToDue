package com.nsmle.todue.utils

import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.nsmle.todue.R
import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.enums.IconColor

fun toggleExpandCollapseView(view: View, expandCollapseBtn: ImageButton, isCollapseTypeGone: Boolean = true) {
	expandCollapseBtn.setOnClickListener {
		var isNotExpand: Boolean = if (isCollapseTypeGone) view.isGone else view.isInvisible
		val viewLayoutParams = view.layoutParams

		if (isNotExpand) {
			expandCollapseBtn.setImageResource(R.drawable.ic_chevron_down)
			view.visibility = View.VISIBLE
			if (!isCollapseTypeGone) viewLayoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
		} else {
			expandCollapseBtn.setImageResource(R.drawable.ic_chevron_up)
			view.visibility = if (isCollapseTypeGone) View.GONE else View.VISIBLE
		}

		view.layoutParams = viewLayoutParams
	}
}

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
		Icon.WALLET   -> R.drawable.ic_wallet
		Icon.CHART    -> R.drawable.ic_chart
		Icon.GIFT     -> R.drawable.ic_gift
		Icon.PLANE    -> R.drawable.ic_paper_plane
		Icon.DATA_PIE -> R.drawable.ic_data_pie
		Icon.PLUS     -> R.drawable.ic_plus
		Icon.MINUS    -> R.drawable.ic_minus
		else          -> R.drawable.ic_all_icons
	}

	return iconAsset
}
