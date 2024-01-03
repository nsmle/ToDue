package com.nsmle.todue.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.location.GnssAntennaInfo.Listener
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nsmle.todue.MainApplication
import com.nsmle.todue.data.entity.Task
import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.enums.IconColor
import com.nsmle.todue.data.repository.TaskDao
import com.nsmle.todue.databinding.DialogAddTaskBinding
import com.nsmle.todue.utils.iconAsset
import com.nsmle.todue.utils.iconColor
import com.nsmle.todue.utils.interfaces.DialogListener

class AddTaskBottomSheetDialogFragment : BottomSheetDialogFragment() {
	private lateinit var app: MainApplication
	private lateinit var binding: DialogAddTaskBinding
	private lateinit var taskDao: TaskDao
	private var dialogListener: DialogListener? = null

	private var iconPicked: Icon? = null
	private var iconColorPicked: IconColor? = null

	companion object {
		const val TAG = "AddTaskDialog"
	}

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val dialog = super.onCreateDialog(savedInstanceState)
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

		app = activity?.application as MainApplication
		binding = DialogAddTaskBinding.inflate(layoutInflater)
		taskDao = app.taskDao

		return dialog
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.inputTaskCategory.setOnClickListener { showCategoryPickerDialog() }
		binding.btnInputTaskIcon.setOnClickListener { showIconPickerDialog() }
		binding.buttonSaveTask.setOnClickListener { saveTask() }
		dialog?.setOnShowListener { view.postDelayed({ focusEditText(binding.inputTaskTitle) }, 75) }
	}

	override fun onDismiss(dialog: DialogInterface) {
		dialogListener?.onAddTaskBottomSheetDialogDismiss()
		dialogListener?.onDismiss(dialog)
		super.onDismiss(dialog)
	}

	fun setListener(listener: DialogListener) {
		dialogListener = listener
	}

	private fun saveTask(): Unit {
		val task = Task(
			title = binding.inputTaskTitle.text.toString(),
			icon = iconPicked,
			iconColor = iconColorPicked
		)
		
		if (validateTask(task))
			dialogListener?.onAddTaskBottomSheetDialogSubmit(task) { resultId -> if (resultId != -1L) dismiss() }
	}

	private fun validateTask(task: Task): Boolean {
		class InputTaskError(val element: View, val message: String)

		var errors = mutableListOf<InputTaskError>()

		if (task.title.count() == 0) errors.add(InputTaskError(binding.inputTaskTitle, "Tugas tidak boleh kosong"))
		if (task.title.isNotEmpty() && task.title.count() < 4) errors.add(InputTaskError(binding.inputTaskTitle, "Tugas harus minimal 4 karakter"))

		if (errors.isNotEmpty()) {
			errors.forEach {
				Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show();
				it.element.requestFocus()
			}

			return false
		}

		return true;
	}

	private fun focusEditText(editText: EditText) {
		editText.requestFocus()

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

	private fun showIconPickerDialog() {
		val iconPickerDialog = IconPickerDialogFragment(iconPicked, iconColorPicked ?: IconColor.PINK, object : DialogListener {
			override fun onIconPickerDialogDismiss(icon: Icon?, iconColor: IconColor?) {
				icon?.let {
					iconPicked = it
					binding.btnInputTaskIcon.setImageResource(iconAsset(it))
				}

				iconColor?.let { color ->
					iconColorPicked = color
					context?.let { ctx ->
						binding.btnInputTaskIcon.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(ctx, iconColor(color).color))
						binding.btnInputTaskIcon.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(ctx, iconColor(color).alphaColor))
					}
				}
			}
		})

		iconPickerDialog.show(childFragmentManager, IconPickerDialogFragment.TAG)
	}
}