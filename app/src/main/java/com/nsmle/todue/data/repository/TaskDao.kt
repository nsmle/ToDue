package com.nsmle.todue.data.repository

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import androidx.room.Update
import com.nsmle.todue.data.entity.Task

@Dao
interface TaskDao {
	@Query("SELECT * FROM task WHERE is_done = false ORDER BY due_at DESC, created_at DESC")
	fun getAll(): MutableList<Task>

	@Query("SELECT * FROM task WHERE is_done = true ORDER BY updated_at DESC")
	fun getDoneTask(): MutableList<Task>

	@Query("SELECT * FROM task WHERE id IN (:userIds)")
	fun getAllByIds(userIds: IntArray): MutableList<Task>

	@Query("SELECT * FROM task WHERE title LIKE :taskTitle LIMIT 1")
	fun findByTitle(taskTitle: String): Task

	@Insert
	fun insertBulk(vararg tasks: Task): MutableList<Long>

	@Insert
	fun insert(task: Task): Long

	@Update
	fun update(task: Task): Int

	@Delete
	fun delete(task: Task): Int
}