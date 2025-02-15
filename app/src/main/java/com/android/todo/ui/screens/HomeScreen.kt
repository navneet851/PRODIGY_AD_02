package com.android.todo.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.android.todo.data.entity.Chip
import com.android.todo.data.entity.Tag
import com.android.todo.data.entity.TodoNote
import com.android.todo.ui.components.ChipSection
import com.android.todo.ui.components.DialogTextField
import com.android.todo.ui.components.NoteTemplate
import com.android.todo.ui.navigation.TodoNoteIndex
import com.android.todo.ui.theme.CustomBlue
import com.android.todo.ui.theme.CustomGreen
import com.android.todo.ui.theme.CustomLightYellow
import com.android.todo.ui.theme.CustomOrange
import com.android.todo.ui.theme.CustomYellow
import com.android.todo.ui.theme.shaded
import com.android.todo.ui.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController, viewModel: TodoViewModel) {
    val todoNotes by viewModel.todosList.collectAsState()
    val chips by viewModel.tagList.collectAsState()

    var todoTagList by rememberSaveable {
        mutableStateOf(listOf(Chip("All", true, 20)))
    }

    Log.d("chips", chips.toString())
    var showDailog by remember {
        mutableStateOf(false)
    }
    var showAddTagDialog by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(chips) {
        val tagList: List<Chip> = chips.map { chip ->
            val count = todoNotes.count { it.tag == chip.tag }
            Chip(tag = chip.tag, selected = false, count = count)
        }
        todoTagList = listOf(Chip("All", true, 20)) + tagList
        Log.d("todoTagList", todoTagList.toString())


    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {


        Spacer(Modifier.padding(16.dp))
        Text(
            modifier = Modifier.padding(16.dp),
            fontSize = 50.sp,
            text = "My \n\nTodo\n\nNotes",
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.padding(16.dp))


        ChipSection(todoTagList) {
            showAddTagDialog = true
        }

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
                val colors =
                    listOf(CustomOrange, CustomYellow, CustomGreen, CustomBlue, CustomLightYellow)
                val color = colors.random()
                NoteTemplate(
                    todoNotes[it],
                    onDelete = {
                        viewModel.deleteTodoNote(todoNotes[it])
                        viewModel.deleteCheckBoxesById(todoNotes[it].id)
                    }
                ) {
                    navController.navigate(TodoNoteIndex(it))
                }
            }
        }
    }

    if (showAddTagDialog) {

        Dialog(
            onDismissRequest = {
                showAddTagDialog = false
            }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                var tag by remember {
                    mutableStateOf("")
                }
                Text(
                    color = CustomBlue,
                    text = "Add Tag",
                    fontWeight = FontWeight.Medium
                )
                DialogTextField(
                    color = CustomBlue,
                    text = tag,
                    onChange = {
                        tag = it
                    }
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomBlue,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.saveTags(Tag(tag = tag))
                            showAddTagDialog = false
                        }

                    }
                ) {
                    Text("Save")
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

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                DialogTextField(
                    text = title,
                    onChange = {
                        title = it
                    },
                    placeholder = "Enter Title"
                )
                Text(
                    color = CustomBlue,
                    text = "Select Tag",
                    fontWeight = FontWeight.Medium
                )
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(90.dp)
                ) {
                    items(todoTagList.size) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(5.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(CustomBlue)
                                .clickable {
                                    tag = todoTagList[it].tag
                                    todoTagList = todoTagList.map {
                                        it.copy(selected = it.tag == tag)
                                    }
                                }
                        ) {
                            Text(
                                color = if (todoTagList[it].selected) Color.White else Color.Gray,
                                text = todoTagList[it].tag
                            )
                        }
                    }
                }

                DialogTextField(
                    text = text,
                    onChange = {
                        text = it
                    }
                )

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomBlue,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.saveTodoNote(TodoNote(title = title, tag = tag, text = text))
                            showDailog = false
                        }

                    }
                ) {
                    Text("Save")
                }

            }

        }
    }

}