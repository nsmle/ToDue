package com.nsmle.todue.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nsmle.todue.R
import com.nsmle.todue.databinding.ActivityMainBinding
import com.nsmle.todue.databinding.DialogAddTaskBinding

class AddTaskBottomSheetDialogFragment : BottomSheetDialogFragment() {
	private lateinit var binding: DialogAddTaskBinding

	companion object {
		const val TAG = "AddTaskDialog"
	}

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val dialog = super.onCreateDialog(savedInstanceState)
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

		binding = DialogAddTaskBinding.inflate(layoutInflater)

		return dialog
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.inputTaskCategory.setOnClickListener { showCategoryPickerDialog() }
		dialog?.setOnShowListener { view.postDelayed({ focusEditText(binding.inputTaskTitle) }, 75) }
	}

	private fun focusEditText(editText: EditText) {
		editText.requestFocus()

		// Tampilkan keyboard menggunakan InputMethodManager
		val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
	}

	private fun setEditTextGapSoftKeyboard(editText: EditText, gap: Int) {
		val marginLayoutParams = editText.layoutParams as ViewGroup.MarginLayoutParams
		marginLayoutParams.bottomMargin = gap;
		editText.layoutParams = marginLayoutParams;
	}

	private fun showCategoryPickerDialog() {
		CategoryPickerDialogFragment().show(childFragmentManager, CategoryPickerDialogFragment.TAG)
	}
}