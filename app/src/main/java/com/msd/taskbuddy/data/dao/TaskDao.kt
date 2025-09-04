package com.msd.taskbuddy.data.dao

import androidx.room.*
import com.msd.taskbuddy.data.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE userEmail = :email")
    fun getTasksForUser(email: String): Flow<List<Task>>

    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}
