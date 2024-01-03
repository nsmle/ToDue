package com.nsmle.todue.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.nsmle.todue.R
import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.enums.IconColor
import com.nsmle.todue.utils.ColorWithAlpha
import com.nsmle.todue.utils.dpToPx
import com.nsmle.todue.utils.iconAsset
import com.nsmle.todue.utils.iconColor
import java.lang.reflect.Array
import kotlin.math.log

class IconColorAdapter(val context: Context, var iconColorPicked: IconColor, val onPick: ((IconColor) -> Unit)? = null) : BaseAdapter() {
	private val iconColors = IconColor.values()

	private class ViewHolder(view: View) {
		val iconColorContainer: CardView = view.findViewById(R.id.icon_color)
	}

	override fun getCount(): Int = iconColors.size

	override fun getItem(position: Int): IconColor = iconColors[position]

	override fun getItemId(position: Int): Long = getItem(position).ordinal.toLong()

	override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
		var itemView = LayoutInflater.from(context).inflate(R.layout.icon_color_item, parent, false)
		var viewHolder = ViewHolder(itemView)
		val iconColorItem = getItem(position)

		setIconColor(IconColor.valueOf(iconColorItem.name), viewHolder.iconColorContainer)
		viewHolder.iconColorContainer.setOnClickListener({ onIconCLiked(iconColorItem, viewHolder) })

		return itemView!!
	}

	private fun setIconColor(iColor: IconColor, iconColorContainer: CardView) {
		iconColorContainer.setCardBackgroundColor(ContextCompat.getColor(context, iconColor(iColor).color))
		iconColorContainer.setBackgroundTintList(ContextCompat.getColorStateList(context, iconColor(iColor).color))
	}

	private fun onIconCLiked(iconColor: IconColor, viewHolder: ViewHolder) {
		onPick?.invoke(iconColor)
		iconColorPicked = iconColor
	}
}