package com.nsmle.todue.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nsmle.todue.data.entity.Task
import com.nsmle.todue.data.repository.TaskDao
import com.nsmle.todue.utils.DATABASE_NAME

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	abstract fun taskDao(): TaskDao

	companion object {
		private val databaseName: String = DATABASE_NAME

		@Volatile
		private var INSTANCE: AppDatabase? = null

		fun getInstance(appContext: Context): AppDatabase {
			return Room.databaseBuilder(
				appContext,
				AppDatabase::class.java,
				databaseName
			).allowMainThreadQueries().build()
		}
	}
}