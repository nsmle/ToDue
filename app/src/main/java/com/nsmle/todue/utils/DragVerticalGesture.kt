package com.nsmle.todue.utils

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class DragVerticalGesture : ItemTouchHelper.Callback() {
	override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
		val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
		return makeMovementFlags(dragFlags, 0)
	}

	override fun onMove(
		recyclerView: RecyclerView,
		viewHolder: RecyclerView.ViewHolder,
		target: RecyclerView.ViewHolder,
	): Boolean {
		return false
	}

	override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
		super.onSelectedChanged(viewHolder, actionState)

		if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
			Log.i("APP", "Item Selected = " + actionState)
			// Implement your logic when an item is selected for drag
		}
	}

	override fun isLongPressDragEnabled(): Boolean {
		return true
	}
}