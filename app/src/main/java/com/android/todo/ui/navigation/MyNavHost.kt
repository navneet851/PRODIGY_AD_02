package com.android.todo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.android.todo.ui.screens.HomeScreen
import com.android.todo.ui.screens.NoteScreen
import com.android.todo.ui.viewmodel.TodoViewModel
import kotlinx.serialization.Serializable

@Composable
fun MyNavHost(viewModel: TodoViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home"){
        composable("home"){
            HomeScreen(navController, viewModel)
        }
        composable<TodoNoteIndex> {
            val todoNoteIndex = it.toRoute<TodoNoteIndex>().id
            NoteScreen(navController, viewModel, todoNoteIndex)
        }
    }
}

@Serializable
data class TodoNoteIndex(
    val id: Int
)