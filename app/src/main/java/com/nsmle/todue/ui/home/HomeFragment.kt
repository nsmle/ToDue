package com.nsmle.todue.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nsmle.todue.MainApplication
import com.nsmle.todue.R
import com.nsmle.todue.adapters.TaskAdapter
import com.nsmle.todue.data.AppDatabase
import com.nsmle.todue.data.repository.TaskDao
import com.nsmle.todue.databinding.FragmentHomeBinding
import com.nsmle.todue.utils.ViewModelFactory

class HomeFragment : Fragment() {

	private var _binding: FragmentHomeBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	private val homeViewModel: HomeViewModel by lazy {
		val factory = ViewModelFactory((activity?.application as MainApplication))
		ViewModelProvider(this, factory).get(HomeViewModel::class.java)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		var application: MainApplication = activity?.application as MainApplication

		// val homeViewModel = ViewModelProvider(this, ViewModelFactory::class.java).get(HomeViewModel::class.java)
		// val viewModelFactory: ViewModelFactory = ViewModelFactory((activity?.application as MainApplication))
		// val homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		val root: View = binding.root

		/**
		 * Task
		 */
		val tasksRv: RecyclerView = binding.tasksRv
		tasksRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
		homeViewModel.tasks.observe(viewLifecycleOwner) {
			tasksRv.setAdapter(TaskAdapter(it))
		}

		/**
		 * Button Expand Task
		 */
		var taskBtnExpandRv: ImageButton = binding.taskCurrentExpandButton
		toggleExpandCollapseRecyclerView(tasksRv, taskBtnExpandRv)

		return root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	fun toggleExpandCollapseRecyclerView(recyclerView: RecyclerView, expandCollapseBtn: ImageButton) {
		expandCollapseBtn.setOnClickListener {
			var isExpand: Boolean = recyclerView.isVisible
			val rvLayoutParams = recyclerView.layoutParams

			if (isExpand) {
				expandCollapseBtn.setImageResource(R.drawable.ic_chevron_up)
				recyclerView.setVisibility(View.INVISIBLE)
				rvLayoutParams.height = 0
			} else {
				expandCollapseBtn.setImageResource(R.drawable.ic_chevron_down)
				recyclerView.setVisibility(View.VISIBLE)
				rvLayoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
			}

			recyclerView.layoutParams = rvLayoutParams
		}
	}
}