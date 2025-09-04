package com.msd.taskbuddy
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.msd.taskbuddy.data.model.Task
import com.msd.taskbuddy.ui.screen.TaskScreen
import com.msd.taskbuddy.ui.viewmodel.TaskViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class TaskScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun addTask_shouldAppearInList() {
        val fakeTasks = MutableStateFlow(listOf<Task>())
        val fakeVM = object : TaskViewModel(null) {
            override val tasks = fakeTasks
            override fun addTask(title: String) {
                fakeTasks.value = fakeTasks.value + Task(title = title, userEmail = "test@mail.com")
            }
        }

        composeTestRule.setContent {
            TaskScreen(
                viewModel = fakeVM,
                userEmail = "test@mail.com",
                onBack = {}
            )
        }

        composeTestRule.onNodeWithText("Enter Task").performTextInput("New Task")
        composeTestRule.onNodeWithText("Add Task").performClick()

        composeTestRule.onNodeWithText("New Task").assertExists()
    }
}
