package com.msd.taskbuddy

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.msd.taskbuddy.ui.screen.LoginScreen
import com.msd.taskbuddy.ui.viewmodel.UserViewModel
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginScreen_shouldShowFields() {
        val fakeVM = mock(UserViewModel::class.java)

        composeTestRule.setContent {
            LoginScreen(
                viewModel = fakeVM,
                onLoginSuccess = {},
                onNavigateToSignup = {}
            )
        }

        composeTestRule.onNodeWithText("Email").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onNodeWithText("Login").assertExists()
    }
}
