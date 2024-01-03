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
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.savedstate.SavedStateRegistryOwner
import com.nsmle.todue.MainApplication
import com.nsmle.todue.data.AppDatabase
import com.nsmle.todue.data.entity.Task
import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.enums.IconColor
import com.nsmle.todue.data.repository.TaskDao
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import com.nsmle.todue.data.entity.Task as TaskEntity

class HomeViewModel(
	val app: MainApplication,
) : ViewModel() {
	private val taskDao: TaskDao = app.taskDao

	private val _tasks = MutableLiveData<MutableList<Task>>()
	val tasks: LiveData<MutableList<Task>> = _tasks

	private val _tasksDone = MutableLiveData<MutableList<Task>>()
	val tasksDone: LiveData<MutableList<Task>> = _tasksDone

	init {
		viewModelScope.launch { refreshData() }
	}

	private fun refreshData() {
		_tasks.value = taskDao.getAll()
		_tasksDone.value = taskDao.getDoneTask()
	}

	private fun clearData() {
		_tasks.value = mutableListOf()
		_tasksDone.value = mutableListOf()
	}

	fun refreshTask(onRefresh: () -> Unit) {
		viewModelScope.async {
			refreshData()
			onRefresh()
		}
	}

	fun insertTask(task: Task, onTaskInsert: ((Long) -> Unit)): Unit {
		viewModelScope.async {
			val result = taskDao.insert(task)
			refreshData()
			onTaskInsert(result)
		}
	}

	fun updateTask(task: Task, onTaskUpdated: ((Boolean) -> Unit)): Unit {
		viewModelScope.async {
			val isUpdated = taskDao.update(task)
			refreshData()
			onTaskUpdated(isUpdated == 1)
		}
	}

	fun deleteTask(task: Task, onTaskDeleted: ((Boolean) -> Unit)) {
		viewModelScope.async {
			val isDeleted = taskDao.delete(task)
			refreshData()
			onTaskDeleted(isDeleted == 1)
		}
	}
}
