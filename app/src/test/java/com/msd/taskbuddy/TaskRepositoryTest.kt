package com.msd.taskbuddy
import com.msd.taskbuddy.data.dao.TaskDao
import com.msd.taskbuddy.data.model.Task
import com.msd.taskbuddy.data.repository.TaskRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.*

class TaskRepositoryTest {

    private val dao = mock(TaskDao::class.java)
    private val repo = TaskRepository(dao)

    @Test
    fun `addTask should call insert on dao`() = runBlocking {
        val email = "test@mail.com"
        repo.addTask("My Task", email)

        verify(dao, times(1)).insertTask(Task(title = "My Task", userEmail = email))
    }
}
