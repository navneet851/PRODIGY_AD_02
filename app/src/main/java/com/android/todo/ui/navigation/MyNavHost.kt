package com.android.todo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.todo.ui.screens.HomeScreen
import com.android.todo.ui.screens.NoteScreen

@Composable
fun MyNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "note"){
        composable("home"){
            HomeScreen()
        }
        composable("note") {
            NoteScreen()
        }
    }
}