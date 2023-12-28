package com.nsmle.todue.data.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nsmle.todue.data.entity.Task

@Dao
interface TaskDao {
	@Query("SELECT * FROM task")
	fun getAll(): List<Task>

	@Query("SELECT * FROM task WHERE id IN (:userIds)")
	fun getAllByIds(userIds: IntArray): List<Task>

	@Query("SELECT * FROM task WHERE title LIKE :taskTitle LIMIT 1")
	fun findByTitle(taskTitle: String): Task

	@Insert
	fun insertAll(vararg tasks: Task)

	@Delete
	fun delete(task: Task)
}