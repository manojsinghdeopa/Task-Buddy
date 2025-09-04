package com.msd.taskbuddy
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.msd.taskbuddy.ui.screen.SignupScreen
import com.msd.taskbuddy.ui.viewmodel.UserViewModel
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class SignupScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun signupScreen_shouldShowFields() {
        val fakeVM = mock(UserViewModel::class.java)

        composeTestRule.setContent {
            SignupScreen(
                viewModel = fakeVM,
                onNavigateToLogin = {}
            )
        }

        composeTestRule.onNodeWithText("Email").assertExists()
        composeTestRule.onNodeWithText("Password").assertExists()
        composeTestRule.onNodeWithText("Signup").assertExists()
    }
}
