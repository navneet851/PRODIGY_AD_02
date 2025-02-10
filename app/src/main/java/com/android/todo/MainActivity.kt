package com.android.todo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.android.todo.data.db.TodoDatabase
import com.android.todo.ui.navigation.MyNavHost
import com.android.todo.ui.theme.TodoTheme
import com.android.todo.ui.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "contacts.db"
        ).build()
    }

    private val viewModel by viewModels<TodoViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TodoViewModel(db.dao) as T
                }
            }
        }
    )

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTheme {
                val todoNotes by viewModel.todosList.collectAsState()
                MyNavHost(viewModel)
            }
        }
    }
}

//fun main() {
//    println(linear_Search(5))
//}
//
//fun linear_Search(find : Int) : String{
//    val list = listOf(1, 2, 3, 4, 5, 6, 6)
//    for (i in list.indices) {
//        if (list[i] == find) {
//            return "value find at $i location"
//        }
//    }
//        return "value not find"
//}