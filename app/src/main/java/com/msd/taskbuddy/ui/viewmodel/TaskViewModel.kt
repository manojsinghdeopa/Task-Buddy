package com.msd.taskbuddy.ui.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msd.taskbuddy.data.model.Task
import com.msd.taskbuddy.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch




open class TaskViewModel(private val repo: TaskRepository?) : ViewModel() {

    private val _currentUserEmail = MutableStateFlow<String?>(null)
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    open val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun setUser(email: String) {
        _currentUserEmail.value = email
        viewModelScope.launch {
            repo?.getTasks(email)?.collect {
                _tasks.value = it
            }
        }
    }

    open fun addTask(title: String) {
        val email = _currentUserEmail.value ?: return
        viewModelScope.launch {
            repo?.addTask(title, email)
        }
    }
}
