package com.msd.taskbuddy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msd.taskbuddy.data.local.DataStoreManager
import com.msd.taskbuddy.data.model.User
import com.msd.taskbuddy.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class ScreenState {
    object Signup : ScreenState()
    object Login : ScreenState()
    object Home : ScreenState()
    object Tasks : ScreenState()
}

class UserViewModel(
    private val repo: UserRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Signup)
    val screenState: StateFlow<ScreenState> = _screenState.asStateFlow()

    private val _loggedInUser = MutableStateFlow<User?>(null)
    val loggedInUser = _loggedInUser.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                dataStore.isFirstLaunch,
                dataStore.isLoggedIn,
                dataStore.userEmail
            ) { firstLaunch, loggedIn, email ->
                when {
                    firstLaunch -> ScreenState.Signup
                    loggedIn && !email.isNullOrBlank() -> {
                        _loggedInUser.value = User(email, "****")
                        ScreenState.Home
                    }
                    else -> ScreenState.Login
                }
            }.collect { state ->
                _screenState.value = state
            }
        }
    }



    fun signup(email: String, password: String) {
        viewModelScope.launch {
            val success = repo.signup(User(email, password))
            if (success) {
                _loggedInUser.value = User(email, password)
                dataStore.saveLoginState(true, email)
                dataStore.setFirstLaunchDone()
                _screenState.value = ScreenState.Home
            } else {
                // show error (user exists, navigate to login)
                _screenState.value = ScreenState.Login
            }
        }
    }


    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = repo.login(email, password)
            if (user != null) {
                _loggedInUser.value = user
                dataStore.saveLoginState(true, email)
                _screenState.value = ScreenState.Home
            } else {
                _screenState.value = ScreenState.Login
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _loggedInUser.value = null
            dataStore.clearSession()
            // dataStore.clear()
            // instead of full clear
            _screenState.value = ScreenState.Login
        }
    }


    fun goToTasks() {
        _screenState.value = ScreenState.Tasks
    }

    fun backToHome() {
        _screenState.value = ScreenState.Home
    }

    fun goToLogin() {
        _screenState.value = ScreenState.Login
    }

    fun goToSignup() {
        _screenState.value = ScreenState.Signup
    }

}
