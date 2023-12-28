package com.nsmle.todue

import android.app.Application
import com.nsmle.todue.data.AppDatabase

class MainApplication : Application() {
	val database by lazy { AppDatabase.getInstance(applicationContext) }
	val taskDao by lazy { database.taskDao() }
}