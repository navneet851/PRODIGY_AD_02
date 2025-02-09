package com.android.todo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.android.todo.data.entity.TodoNote
import com.android.todo.ui.screens.HomeScreen
import com.android.todo.ui.screens.NoteScreen

@Composable
fun MyNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home"){
        composable("home"){
            HomeScreen(navController)
        }
        composable<TodoNote> {
            val todoNote = it.toRoute<TodoNote>()
            NoteScreen(navController, todoNote)
        }
    }
}