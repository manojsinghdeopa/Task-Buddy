package com.msd.taskbuddy.data.repository
import com.msd.taskbuddy.data.dao.TaskDao
import com.msd.taskbuddy.data.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    fun getTasks(email: String): Flow<List<Task>> = dao.getTasksForUser(email)

    suspend fun addTask(title: String, email: String) {
        dao.insertTask(Task(title = title, userEmail = email))
    }
}
