package com.android.todo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.todo.ui.screens.HomeScreen
import com.android.todo.ui.screens.NoteScreen
import com.android.todo.ui.viewmodel.TodoViewModel

@Composable
fun MyNavHost(viewModel: TodoViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home"){
        composable("home"){
            HomeScreen(navController, viewModel)
        }
        composable("note") {
            NoteScreen(navController)
        }
    }
}