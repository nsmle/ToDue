package com.nsmle.todue.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nsmle.todue.R
import com.nsmle.todue.adapters.CategoryAdapter
import com.nsmle.todue.adapters.TaskAdapter
import com.nsmle.todue.data.Category
import com.nsmle.todue.data.Task
import com.nsmle.todue.databinding.FragmentHomeBinding
import com.nsmle.todue.utils.dpToPx

class HomeFragment : Fragment() {

	private var _binding: FragmentHomeBinding? = null

	// This property is only valid between onCreateView and
	// onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		val root: View = binding.root

		// val categoriesRv: RecyclerView = binding.categoriesRv
		//
		// val categories = mutableListOf<Category>(
		// 	Category("Urgent", R.color.white, R.color.pink, false, "", "", 1),
		// 	Category("Sekolah", R.color.white, R.color.purple, false, "", "", 1),
		// 	Category("Tugas", R.color.white, R.color.blue, false, "", "", 1),
		// 	Category("Kuliah", R.color.white, R.color.teal, false, "", "", 1),
		// 	Category("Kerjaan", R.color.white, R.color.blue, false, "", "", 1),
		// )
		//
		// val categoriesRvAdapter = CategoryAdapter(categories)
		// categoriesRv.layoutManager =
		// 	LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
		// categoriesRv.setAdapter(categoriesRvAdapter)

		// val textView: TextView = binding.textHome
		// homeViewModel.text.observe(viewLifecycleOwner) {
		//     textView.text = it
		// }

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
			Log.i("APP", "Button Expand = " + isExpand)
			val layoutParamsRv = recyclerView.layoutParams

			if (isExpand) {
				expandCollapseBtn.setImageResource(R.drawable.ic_chevron_up)
				recyclerView.setVisibility(View.INVISIBLE)
				layoutParamsRv.height = 0
			} else {
				expandCollapseBtn.setImageResource(R.drawable.ic_chevron_down)
				recyclerView.setVisibility(View.VISIBLE)
				layoutParamsRv.height = LinearLayout.LayoutParams.MATCH_PARENT
			}

			recyclerView.layoutParams = layoutParamsRv
		}
	}
}