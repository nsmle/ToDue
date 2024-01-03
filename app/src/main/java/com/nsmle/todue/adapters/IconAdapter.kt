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

class IconAdapter(val context: Context, val iconColor: IconColor, var selectedIcon: Icon? = null, val onPick: ((Icon) -> Unit)? = null) : BaseAdapter() {
	private val icons = Icon.values()
	private var selectedIconHolder: ViewHolder? = null

	private class ViewHolder(view: View) {
		val iconImage: ImageView = view.findViewById(R.id.icon)
		val iconContainer: CardView = view.findViewById(R.id.icon_container)
	}

	override fun getCount(): Int = icons.size

	override fun getItem(position: Int): Icon = icons[position]

	override fun getItemId(position: Int): Long = getItem(position).ordinal.toLong()

	override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
		var itemView = LayoutInflater.from(context).inflate(R.layout.icon_item, parent, false)
		var viewHolder = ViewHolder(itemView)
		val iconItem = getItem(position)

		viewHolder.iconImage.setImageResource(iconAsset(iconItem))
		setIconColor(iconColor, viewHolder.iconImage, viewHolder.iconContainer)

		viewHolder.iconImage.setOnClickListener({ onIconCLiked(iconItem, viewHolder) })
		viewHolder.iconContainer.setOnClickListener({ onIconCLiked(iconItem, viewHolder) })

		return itemView!!
	}

	private fun setIconColor(iColor: IconColor, iconImage: ImageView, iconContainer: CardView) {
		iconImage.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, iconColor(iColor).color))
		iconContainer.setCardBackgroundColor(ContextCompat.getColor(context, iconColor(iColor).alphaColor))
		iconContainer.setBackgroundTintList(ContextCompat.getColorStateList(context, iconColor(iColor).alphaColor))
	}

	private fun onIconCLiked(icon: Icon, viewHolder: ViewHolder) {
		onPick?.invoke(icon)
		selectedIconHolder?.let { setIconColor(iconColor, selectedIconHolder!!.iconImage, selectedIconHolder!!.iconContainer) }

		selectedIcon = icon
		selectedIconHolder = viewHolder
		setIconColor(IconColor.values().random(), viewHolder.iconImage, viewHolder.iconContainer)
	}
}