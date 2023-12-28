package com.nsmle.todue.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nsmle.todue.MainApplication
import com.nsmle.todue.ui.home.HomeViewModel

class ViewModelFactory(val app: MainApplication) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return HomeViewModel(app) as T
		}

		throw IllegalArgumentException("Unable to construct view model")
	}
}