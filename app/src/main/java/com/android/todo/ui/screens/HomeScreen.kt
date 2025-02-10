package com.android.todo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.android.todo.data.entity.Chip
import com.android.todo.data.entity.TodoNote
import com.android.todo.ui.components.ChipSection
import com.android.todo.ui.components.NoteTemplate
import com.android.todo.ui.theme.CustomBlue
import com.android.todo.ui.theme.CustomGreen
import com.android.todo.ui.theme.CustomLightYellow
import com.android.todo.ui.theme.CustomOrange
import com.android.todo.ui.theme.CustomYellow
import com.android.todo.ui.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, viewModel: TodoViewModel) {
    val todoNotes by viewModel.todosList.collectAsState()


    var showDailog by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        var chips = listOf(
            Chip("All", true, 20),
            Chip("Work", false, 15),
            Chip("Personal", false, 34),
            Chip("Personal", false, 34),
            Chip("Personal", false, 34),
            Chip("0", false, 34)
        )

        val colors = listOf(CustomOrange, CustomYellow, CustomGreen, CustomBlue, CustomLightYellow)
        Spacer(Modifier.padding(16.dp))
        Text(
            modifier = Modifier.padding(16.dp),
            fontSize = 50.sp,
            text = "My \n\nTodo\n\nNotes",
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.padding(16.dp))


        ChipSection(chips)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(280.dp)
                        .padding(5.dp)
                        .border(2.dp, Color.DarkGray, RoundedCornerShape(30.dp))
                        .padding(10.dp)
                        .clickable {
                            showDailog = true
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            items(todoNotes.size) {
                NoteTemplate(todoNotes[it]) {
                    navController.navigate("note")
                }
            }

        }

    }

    if (showDailog) {
        Dialog(
            onDismissRequest = {
                showDailog = false
            }
        ) {
            var title by remember { mutableStateOf("") }
            var tag by remember { mutableStateOf("") }
            var text by remember { mutableStateOf("") }

            Column {
                TextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    label = {
                        Text("Title")
                    }
                )
                TextField(
                    value = tag,
                    onValueChange = {
                        tag = it
                    },
                    label = {
                        Text("Tag")
                    }
                )
                TextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    label = {
                        Text("Text")
                    }
                )

                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.saveTodoNote(TodoNote(title = title, tag = tag, text = text))
                            showDailog = false
                        }

                    }
                ) {
                    Text("Save")
                }

                Button(
                    onClick = {
                        showDailog = false
                    }
                ) {
                    Text("Cancel")
                }
            }

        }
    }

}