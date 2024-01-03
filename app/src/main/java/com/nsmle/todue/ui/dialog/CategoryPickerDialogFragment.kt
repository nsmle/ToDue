package com.nsmle.todue.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.nsmle.todue.R
import com.nsmle.todue.databinding.DialogCategoryPickerBinding
import com.nsmle.todue.databinding.DialogIconPickerBinding

class CategoryPickerDialogFragment : DialogFragment() {

	private lateinit var binding: DialogCategoryPickerBinding

	companion object {
		const val TAG = "CategoryPickerDialog"
	}

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val dialog = super.onCreateDialog(savedInstanceState)
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
		binding = DialogCategoryPickerBinding.inflate(layoutInflater)

		return dialog
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
	}
}