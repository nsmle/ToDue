package com.nsmle.todue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nsmle.todue.data.Task
import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.enums.IconColor

class HomeViewModel : ViewModel() {
	private val _tasks = MutableLiveData<MutableList<Task>>().apply {
		value = mutableListOf<Task>(
			Task("Belanja Bulanan Kebutuhan", null, Icon.PLUS, IconColor.PURPLE, null, false, null, null, 1),
			Task("Tugas Kuliah", null, null, null, null, false, null, null, 2),
			Task("Belajar Melukis", null, null, null, null, false, null, null, 3),
			Task("Masak", null, null, null, null, false, null, null, 4),
			Task("Belanja Bulanan", null, null, null, null, false, null, null, 1),
			Task("Tugas Kuliah", null, null, null, null, false, null, null, 2),
			Task("Belajar Melukis", null, null, null, null, false, null, null, 3),
			Task("Masak", null, null, null, null, false, null, null, 4),
			Task("Belanja Bulanan", null, null, null, null, false, null, null, 1),
			Task("Tugas Kuliah", null, null, null, null, false, null, null, 2),
			Task("Belajar Melukis", null, null, null, null, false, null, null, 3),
			Task("Masak", null, null, null, null, false, null, null, 4),
			Task("Belanja Bulanan", null, null, null, null, false, null, null, 1),
			Task("Tugas Kuliah", null, null, null, null, false, null, null, 2),
			Task("Belajar Melukis", null, null, null, null, false, null, null, 3),
			Task("Masak", null, null, null, null, false, null, null, 4),
		);
	}

	val tasks: LiveData<MutableList<Task>> = _tasks
}