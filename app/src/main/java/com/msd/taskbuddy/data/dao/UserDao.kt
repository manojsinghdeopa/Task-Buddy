package com.msd.taskbuddy.data.dao

import androidx.room.*
import com.msd.taskbuddy.data.model.User


@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
}

