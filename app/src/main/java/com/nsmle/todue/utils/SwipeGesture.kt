package com.nsmle.todue.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeGesture(var disableSwipeRight: Boolean = false, var disableSwipeLeft: Boolean = false) : ItemTouchHelper.Callback() {
	override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
		var swipeFlag = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT;

		if (disableSwipeRight) {
			swipeFlag = ItemTouchHelper.LEFT
		} else if (disableSwipeLeft) {
			swipeFlag = ItemTouchHelper.RIGHT
		}

		return makeMovementFlags(0, swipeFlag)
	}

	override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
		return false
	}
}