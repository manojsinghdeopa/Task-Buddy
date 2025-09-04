package com.msd.taskbuddy.data.repository
import com.msd.taskbuddy.data.dao.UserDao
import com.msd.taskbuddy.data.model.User

class UserRepository(private val dao: UserDao) {

    suspend fun signup(user: User): Boolean {
        val existing = dao.getUserByEmail(user.email)
        return if (existing == null) {
            dao.insertUser(user)
            true
        } else {
            false // user already exists
        }
    }

    suspend fun login(email: String, password: String): User? {
        val user = dao.getUserByEmail(email)
        return if (user != null && user.password == password) user else null
    }
}

