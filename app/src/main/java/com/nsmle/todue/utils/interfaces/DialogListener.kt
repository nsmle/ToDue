package com.nsmle.todue.utils.interfaces

import android.content.DialogInterface
import android.util.Log
import com.nsmle.todue.data.entity.Task
import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.enums.IconColor

interface DialogListener {
	fun onAddTaskBottomSheetDialogDismiss() {}
	fun onAddTaskBottomSheetDialogSubmit(task: Task? = null, onSaved: (Long) -> Unit) {}
	fun onDismiss(dialog: DialogInterface) {}

	fun onIconPickerDialogDismiss(icon: Icon?, iconColor: IconColor?) {}
}