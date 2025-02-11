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
            "todo.db"
        ).build()
    }

    private val viewModel by viewModels<TodoViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TodoViewModel(db.todoDao) as T
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

fun main() {
    val stack = Stack(6)
    stack.print()
    println(stack.push(999))
    println(stack.push(234))
    stack.print()
    println(stack.pop())
    stack.print()
}


class Stack(
    private val max: Int = 0
) {
    private var top: Int? = null
    private val stack: IntArray = IntArray(max){-1}

    fun isEmpty() = top == null

    fun isFull() = top == max

    fun push(data: Int): String {
        if (isFull())
            return "overflow"
        if (isEmpty()) {
            stack[0] = data
            top = 0
            return "Pushed"
        } else {
            top = top?.plus(1)
            stack[top!!] = data
            return "Pushed"
        }
    }

    fun pop() : String{
        if (isEmpty()) {
            return "Empty Stack"
        } else {
            stack[top!!] = -1
            top = top?.minus(1)
            return "Poped"
        }
    }

    fun print(){
        println(stack.contentToString())
    }
}