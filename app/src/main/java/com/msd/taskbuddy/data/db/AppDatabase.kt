package com.msd.taskbuddy.data.db



import androidx.room.Database
import androidx.room.RoomDatabase
import com.msd.taskbuddy.data.dao.TaskDao
import com.msd.taskbuddy.data.dao.UserDao
import com.msd.taskbuddy.data.model.Task
import com.msd.taskbuddy.data.model.User

// App Database with User + Task tables
@Database(entities = [Task::class, User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao
}
