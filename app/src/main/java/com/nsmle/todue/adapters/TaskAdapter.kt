package com.nsmle.todue.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nsmle.todue.R
import com.nsmle.todue.data.Task
import com.nsmle.todue.utils.dpToPx
import com.nsmle.todue.utils.iconAsset
import com.nsmle.todue.utils.iconColor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class TaskAdapter(private val tasks: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

	class ViewHolder(taskItemView: View) : RecyclerView.ViewHolder(taskItemView) {
		val context: Context
		val taskTitle: TextView;
		val taskIcon: ImageView
		val taskDeadLine: TextView;
		val taskIconWrapper: CardView;
		val taskContentWrapper: ConstraintLayout;

		init {
			context = taskItemView.context
			taskTitle = taskItemView.findViewById(R.id.task_title)
			taskIcon = taskItemView.findViewById(R.id.task_icon)
			taskDeadLine = taskItemView.findViewById(R.id.task_deadline)
			taskIconWrapper = taskItemView.findViewById(R.id.task_icon_wrapper)
			taskContentWrapper = taskItemView.findViewById(R.id.task_content_wrapper)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val taskItemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
		return ViewHolder(taskItemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val task = tasks[position]
		holder.taskTitle.text = task.title

		if (task.icon != null && task.iconColor != null) {
			holder.taskIcon.setImageResource(iconAsset(task.icon))
			holder.taskIcon.setImageTintList(ContextCompat.getColorStateList(holder.context, iconColor(task.iconColor).color))

			holder.taskIconWrapper.setCardBackgroundColor(iconColor(task.iconColor).alphaColor)
			holder.taskIconWrapper.setBackgroundTintList(ContextCompat.getColorStateList(holder.context, iconColor(task.iconColor).alphaColor))

			val wrapperLayoutParams = holder.taskContentWrapper.layoutParams as ViewGroup.MarginLayoutParams
			wrapperLayoutParams.topMargin = dpToPx(5, holder.context)
			wrapperLayoutParams.bottomMargin = dpToPx(5, holder.context)
		} else {
			holder.taskIconWrapper.visibility = View.GONE;
		}

		holder.taskDeadLine.text = LocalDateTime.parse(task.createdAt).format(DateTimeFormatter.ofPattern("HH.mm, dd MMM yyyy", Locale.ENGLISH))

		// Mis category
	}

	override fun getItemCount(): Int = tasks.size
}