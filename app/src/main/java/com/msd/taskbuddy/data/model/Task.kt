package com.msd.taskbuddy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// Task Entity for task list
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val userEmail: String   // 👈 Link task to specific user
)

