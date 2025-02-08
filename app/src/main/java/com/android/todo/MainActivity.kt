package com.android.todo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.todo.ui.navigation.MyNavHost
import com.android.todo.ui.screens.HomeScreen
import com.android.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTheme {
                MyNavHost()
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