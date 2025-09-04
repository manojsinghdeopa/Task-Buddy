package com.msd.taskbuddy
import com.msd.taskbuddy.data.local.DataStoreManager
import com.msd.taskbuddy.data.model.User
import com.msd.taskbuddy.data.repository.UserRepository
import com.msd.taskbuddy.ui.viewmodel.ScreenState
import com.msd.taskbuddy.ui.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var repo: UserRepository
    private lateinit var dataStore: DataStoreManager
    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repo = mock(UserRepository::class.java)
        dataStore = mock(DataStoreManager::class.java)

        `when`(dataStore.isFirstLaunch).thenReturn(flowOf(false))
        `when`(dataStore.isLoggedIn).thenReturn(flowOf(false))
        `when`(dataStore.userEmail).thenReturn(flowOf(null))

        viewModel = UserViewModel(repo, dataStore)
    }

    @Test
    fun `signup should save user and update state`() = runTest {
        `when`(repo.signup(User("test@mail.com", "1234"))).thenReturn(true)

        viewModel.signup("test@mail.com", "1234")
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals("test@mail.com", viewModel.loggedInUser.value?.email)
        assert(viewModel.screenState.value is ScreenState.Home)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
