package com.nsmle.todue.adapters

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.marginLeft
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import com.nsmle.todue.R
import com.nsmle.todue.data.Category

class CategoryAdapter(private val categories: MutableList<Category>) :
	RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val categoryTitle: TextView
		val context: Context;

		init {
			// Define click listener for the ViewHolder's View.
			categoryTitle = itemView.findViewById(R.id.category_title)
			context = itemView.context
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
		val itemView =
			LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
		return ViewHolder(itemView)
	}

	override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
		val itemViewParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
		val density = holder.context.resources.displayMetrics.density
		val category = categories[position]

		Log.i("APP", "density = " + density)
		Log.i("APP", "Pos = " + position)
		Log.i("APP", "Color = " + category.color)

		when (position) {
			0                   -> itemViewParams.setMargins((16 * density).toInt(), 0, 0, 0)
			categories.size - 1 -> itemViewParams.setMargins((8 * density).toInt(), 0, (16 * density).toInt(), 0)
			else                -> itemViewParams.setMargins((8 * density).toInt(), 0, 0, 0)
		}

		holder.categoryTitle.text = category.title
		holder.categoryTitle.setTextColor(ContextCompat.getColor(holder.context, category.color))
		holder.itemView.backgroundTintList = ContextCompat.getColorStateList(holder.context, category.background)
	}

	override fun getItemCount(): Int = categories.size
}