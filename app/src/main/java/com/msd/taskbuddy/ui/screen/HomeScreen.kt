package com.msd.taskbuddy.ui.screen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.msd.taskbuddy.ui.viewmodel.UserViewModel

@Composable
fun HomeScreen(viewModel: UserViewModel, onLogout: () -> Unit, onOpenTasks: () -> Unit) {
    Column(Modifier.padding(16.dp)) {
        Text("Welcome, ${viewModel.loggedInUser.value?.email}", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onOpenTasks) {
            Text("Go to Tasks")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            viewModel.logout()
            onLogout()
        }) {
            Text("Logout")
        }
    }
}

