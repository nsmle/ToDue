package com.nsmle.todue.ui.dialog

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.fragment.app.DialogFragment
import com.nsmle.todue.MainApplication
import com.nsmle.todue.R
import com.nsmle.todue.adapters.IconAdapter
import com.nsmle.todue.adapters.IconColorAdapter
import com.nsmle.todue.adapters.TaskAdapter
import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.enums.IconColor
import com.nsmle.todue.databinding.DialogAddTaskBinding
import com.nsmle.todue.databinding.DialogIconPickerBinding
import com.nsmle.todue.utils.dpToPx
import com.nsmle.todue.utils.iconAsset
import com.nsmle.todue.utils.iconColor
import com.nsmle.todue.utils.interfaces.DialogListener

class IconPickerDialogFragment(
	var iconPicked: Icon? = null,
	var iconColorPicked: IconColor = IconColor.PINK,
	var dialogListener: DialogListener? = null,
) :
	DialogFragment() {
	private lateinit var binding: DialogIconPickerBinding

	companion object {
		const val TAG = "IconPickerDialog"
	}

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val dialog = super.onCreateDialog(savedInstanceState)
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
		binding = DialogIconPickerBinding.inflate(layoutInflater)

		return dialog
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		updateIconPicked()

		binding.iconsGv.adapter = IconAdapter(view.context, iconColorPicked, iconPicked) { icon ->
			iconPicked = icon
			updateIconPicked()
		}

		binding.iconColorsGv.adapter = IconColorAdapter(view.context, iconColorPicked) { iconColor ->
			iconColorPicked = iconColor
			updateIconPicked()

			binding.iconsGv.adapter = IconAdapter(view.context, iconColorPicked, iconPicked) { icon ->
				iconPicked = icon
				updateIconPicked()
			}
		}
	}

	override fun onDismiss(dialog: DialogInterface) {
		super.onDismiss(dialog)
		dialogListener?.onIconPickerDialogDismiss(iconPicked, iconColorPicked)
	}

	private fun updateIconPicked() {
		iconPicked?.let { icon ->
			if (binding.iconPickedWrapper.visibility == View.GONE)
				binding.iconPickedWrapper.visibility = View.VISIBLE

			binding.iconPicked.setImageResource(iconAsset(icon))
			context?.let { ctx ->
				binding.iconPicked.imageTintList = ColorStateList.valueOf(ContextCompat.getColor(ctx, iconColor(iconColorPicked).color))
				binding.iconPickedContainer.setCardBackgroundColor(ContextCompat.getColor(ctx, iconColor(iconColorPicked).alphaColor))
				binding.iconPickedContainer.setBackgroundTintList(ContextCompat.getColorStateList(ctx, iconColor(iconColorPicked).alphaColor))
			}
		}
	}
}
