package com.nsmle.todue.ui.home

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.savedstate.SavedStateRegistryOwner
import com.nsmle.todue.MainApplication
import com.nsmle.todue.data.AppDatabase
import com.nsmle.todue.data.Task
import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.enums.IconColor
import com.nsmle.todue.data.repository.TaskDao
import com.nsmle.todue.data.entity.Task as TaskEntity

class HomeViewModel(
	val app: MainApplication,
) : ViewModel() {

	private val taskDao: TaskDao = app.taskDao

	private val _tasks = MutableLiveData<MutableList<Task>>().apply {
		value = mutableListOf<Task>(
			Task("Tugas Kuliah", null, Icon.CHART, IconColor.TEAL, null, false, null, null, 2),
			Task("Masak", null, Icon.CHART, IconColor.PINK, null, false, null, null, 4),
			Task("Belanja Bulanan", null, Icon.CHART, IconColor.BLUE, null, false, null, null, 1),
			Task("Belajar Melukis", null, Icon.CHART, IconColor.TEAL_LIGHT, null, false, null, null, 3),
			Task("Tugas Kuliah", null, null, null, null, false, null, null, 2),
			Task("Belajar Melukis", null, null, null, null, false, null, null, 3),
			Task("Masak", null, null, null, null, false, null, null, 4),
			Task("Belanja Bulanan", null, null, null, null, false, null, null, 1),
			Task("Belanja Bulanan Kebutuhan", null, Icon.PLUS, IconColor.PURPLE, null, false, null, null, 1),
			Task("Tugas Kuliah", null, null, null, null, false, null, null, 2),
			Task("Belajar Melukis", null, null, null, null, false, null, null, 3),
			Task("Tugas Kuliah", null, Icon.CHART, IconColor.TEAL, null, false, null, null, 2),
			Task("Masak", null, null, null, null, false, null, null, 4),
			Task("Belanja Bulanan", null, null, null, null, false, null, null, 1),
			Task("Tugas Kuliah", null, null, null, null, false, null, null, 2),
			Task("Belajar Melukis", null, null, null, null, false, null, null, 3),
			Task("Masak", null, null, null, null, false, null, null, 4),
		);
	}

	val tasks: LiveData<MutableList<Task>> = _tasks
}
