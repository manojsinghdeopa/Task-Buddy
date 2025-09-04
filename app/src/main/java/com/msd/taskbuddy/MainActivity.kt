package com.msd.taskbuddy
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.room.Room
import com.msd.taskbuddy.data.db.AppDatabase
import com.msd.taskbuddy.data.local.DataStoreManager
import com.msd.taskbuddy.data.repository.TaskRepository
import com.msd.taskbuddy.data.repository.UserRepository
import com.msd.taskbuddy.ui.screen.*
import com.msd.taskbuddy.ui.viewmodel.ScreenState
import com.msd.taskbuddy.ui.viewmodel.TaskViewModel
import com.msd.taskbuddy.ui.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "task_db"
        ).build()

        val userRepo = UserRepository(db.userDao())
        val taskRepo = TaskRepository(db.taskDao())

        val dataStore = DataStoreManager(applicationContext)
        val userVM = UserViewModel(userRepo, dataStore)
        val taskVM = TaskViewModel(taskRepo)

        setContent {
            val currentScreen by userVM.screenState.collectAsState()

            when (currentScreen) {
                is ScreenState.Signup -> SignupScreen(
                    viewModel = userVM,
                    onNavigateToLogin = { userVM.goToLogin() }
                )
                is ScreenState.Login -> LoginScreen(
                    viewModel = userVM,
                    onLoginSuccess = { /* handled by state change */ },
                    onNavigateToSignup = { userVM.goToSignup() }
                )
                is ScreenState.Home -> HomeScreen(
                    viewModel = userVM,
                    onLogout = { userVM.logout() },
                    onOpenTasks = { userVM.goToTasks() }
                )

                is ScreenState.Tasks -> {
                    val userEmail = userVM.loggedInUser.value?.email ?: ""
                    TaskScreen(
                        viewModel = taskVM,
                        userEmail = userEmail,
                        onBack = { userVM.backToHome() }
                    )
                }

            }

        }


    }
}
