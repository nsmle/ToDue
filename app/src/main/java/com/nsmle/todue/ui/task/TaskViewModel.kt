package com.nsmle.todue.ui.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsmle.todue.MainApplication
import com.nsmle.todue.data.AppDatabase
import com.nsmle.todue.data.repository.TaskDao

class TaskViewModel : ViewModel() {

	private val _text = MutableLiveData<String>().apply {
		value = "This is task Fragment"
	}
	val text: LiveData<String> = _text
}