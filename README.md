# ✅ Task Buddy

A **sample Task Management app** built with **Kotlin**, **Jetpack Compose**, and **Room Database**.  
The project follows **modern Android development practices** including **MVVM architecture**, **Repository pattern**, and comes with **unit tests** & **UI tests**.

---

## 🚀 Features

- ➕ Add new tasks  
- 📋 View list of tasks  
- ✏️ Edit existing tasks  
- 🗑 Delete tasks  
- 🛠 Built using **Jetpack Compose** for UI  
- 🗄 **Room Database** integration for offline storage  
- 🧩 **MVVM Architecture** with Repository pattern  
- ✅ Unit tests & UI tests for reliability  

---

## 🏗 Project Structure

```
com.msd.taskbuddy
│
├── data
│ ├── dao # DAO interfaces (e.g., TaskDao.kt)
│ ├── database # Room database class (TaskDatabase.kt)
│ ├── model # Data models/entities (Task.kt)
│ └── repository # Repository layer (TaskRepository.kt)
│
├── ui
│ ├── components # Reusable UI components (TaskItem, TaskList, AddTaskForm)
│ ├── screens # Screens (TaskListScreen, AddTaskScreen)
│ ├── theme # Theming (Color.kt, Theme.kt, Type.kt)
│ └── viewmodel # ViewModel classes (TaskViewModel.kt)
│
└── MainActivity.kt # App entry point, sets up NavHost & ViewModel

```
