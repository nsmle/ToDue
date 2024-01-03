package com.nsmle.todue.ui.home

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nsmle.todue.MainApplication
import com.nsmle.todue.adapters.TaskAdapter
import com.nsmle.todue.data.entity.Task
import com.nsmle.todue.databinding.FragmentHomeBinding
import com.nsmle.todue.utils.ViewModelFactory
import com.nsmle.todue.utils.interfaces.DialogListener
import com.nsmle.todue.utils.toggleExpandCollapseView

class HomeFragment : Fragment(), DialogListener {

	private var _binding: FragmentHomeBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	private val viewModel: HomeViewModel by lazy {
		val factory = ViewModelFactory((activity?.application as MainApplication))
		ViewModelProvider(this, factory).get(HomeViewModel::class.java)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)

		/**
		 * Task
		 */
		val tasksRv: RecyclerView = binding.tasksRv
		var taskBtnExpandRv: ImageButton = binding.taskCurrentExpandButton
		var textTasksNotFound: TextView = binding.textTasksNotFound
		tasksRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

		viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
			binding.textSummaryTaskSecondary.text = tasks.count().toString()

			if (tasks.isEmpty()) {
				tasksRv.visibility = View.GONE
				textTasksNotFound.visibility = View.VISIBLE
				toggleExpandCollapseView(textTasksNotFound, taskBtnExpandRv)
			} else {
				toggleExpandCollapseView(tasksRv, taskBtnExpandRv)

				val tasksAdapter: TaskAdapter = TaskAdapter(tasks)
				tasksAdapter.handleSwipe(tasksRv,
					onSwipeRight = { task -> deleteTask(task) },
					onSwipeLeft = { task -> markTaskAsDone(task) }
				)

				tasksRv.setAdapter(tasksAdapter)
			}

			/**
			 * Task Done
			 */
			val tasksDoneWrapper: ConstraintLayout = binding.wrapperTasksDone
			val tasksDoneRv: RecyclerView = binding.tasksDoneRv
			var taskDoneBtnExpandRv: ImageButton = binding.taskDoneExpandButton
			tasksDoneRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

			viewModel.tasksDone.observe(viewLifecycleOwner) { tasksDone ->
				binding.textSummaryTaskPrimary.text = tasksDone.count().toString()

				if (tasksDone.isEmpty()) {
					tasksDoneRv.visibility = View.GONE
					tasksDoneWrapper.visibility = View.GONE
				} else {
					toggleExpandCollapseView(tasksDoneRv, taskDoneBtnExpandRv)

					val tasksDoneAdapter = TaskAdapter(tasksDone)
					tasksDoneAdapter.handleSwipe(tasksDoneRv, onSwipeRight = { task -> deleteTask(task) })

					tasksDoneRv.setAdapter(tasksDoneAdapter)
				}
			}
		}

		/**
		 * Handling Refresh
		 */
		val swipeRefreshLayout = binding.swipeRefreshLayout
		swipeRefreshLayout.setOnRefreshListener {
			swipeRefreshLayout.isRefreshing = false

			val fragmentTransaction = parentFragmentManager.beginTransaction()
			if (VERSION.SDK_INT >= VERSION_CODES.N) {
				fragmentTransaction.detach(this).commitNow()
				fragmentTransaction.attach(this).commitNow()
			} else {
				fragmentTransaction.detach(this).attach(this).commit()
			}
		}


		return binding.root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	override fun onAddTaskBottomSheetDialogSubmit(task: Task?, onSaved: (Long) -> Unit) {
		if (task == null)
			return Toast.makeText(context, "Gagal menambahkan tugas", Toast.LENGTH_SHORT).show()

		viewModel.insertTask(task) { createdId ->
			if (createdId != -1L) onSaved(createdId)
			var toastMessage = if (createdId != -1L)
				"Tugas baru ditambahkan" else
				"Gagal menambahkan tugas";

			Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
		}
	}

	fun markTaskAsDone(task: Task) {
		task.isDone = true
		viewModel.updateTask(task) { taskUpdated ->
			var toastMessage = if (taskUpdated)
				"${task.title} selesai" else
				"${task.title} gagal di update";

			Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
		}
	}

	fun deleteTask(task: Task): Unit {
		viewModel.deleteTask(task) { isDeleted ->
			var toastMessage = if (isDeleted)
				"${task.title} dihapus" else
				"${task.title} gagal di hapus";

			Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
		}
	}
}