package com.nsmle.todue.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nsmle.todue.data.converters.DateConverter
import com.nsmle.todue.data.converters.IconColorConverter
import com.nsmle.todue.data.converters.IconConverter
import com.nsmle.todue.data.entity.Task
import com.nsmle.todue.data.enums.Icon
import com.nsmle.todue.data.repository.TaskDao
import com.nsmle.todue.utils.DATABASE_NAME

@Database(entities = [Task::class], version = 1)
@TypeConverters(DateConverter::class, IconConverter::class, IconColorConverter::class)
abstract class AppDatabase : RoomDatabase() {
	abstract fun taskDao(): TaskDao

	companion object {
		private val databaseName: String = DATABASE_NAME

		@Volatile
		private var INSTANCE: AppDatabase? = null

		fun getInstance(appContext: Context): AppDatabase {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					appContext.applicationContext,
					AppDatabase::class.java,
					databaseName
				)
					.addTypeConverter(DateConverter())
					.addTypeConverter(IconConverter())
					.addTypeConverter(IconColorConverter())
					.allowMainThreadQueries()
					.fallbackToDestructiveMigration()
					.build()
				INSTANCE = instance
				instance
			}
		}
	}
}