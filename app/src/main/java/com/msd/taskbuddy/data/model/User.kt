package com.msd.taskbuddy.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

// User Entity for signup/login
@Entity(tableName = "users")
data class User(
    @PrimaryKey val email: String,
    val password: String
)
