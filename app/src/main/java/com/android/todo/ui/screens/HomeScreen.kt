package com.android.todo.ui.screens

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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

    var selectedTag by rememberSaveable { mutableStateOf("All") }
    var todoTagList by rememberSaveable { mutableStateOf(listOf<Chip>()) }

    val context = LocalContext.current

    Log.d("chips", chips.toString())
    var showDailog by remember {
        mutableStateOf(false)
    }
    var showAddTagDialog by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(chips, todoNotes) {
        val tagList: List<Chip> = chips.map { chip ->
            val count = todoNotes.count { it.tag == chip.tag }
            Chip(tag = chip.tag, selected = chip.tag == selectedTag, count = count)
        }
        todoTagList = listOf(Chip("All", selectedTag == "All", todoNotes.size)) + tagList
        Log.d("todoTagList", todoTagList.toString())

    }

    val filteredTodoNotes = if (selectedTag == "All") {
        todoNotes
    } else {
        todoNotes.filter { it.tag == selectedTag }
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


        ChipSection(
            chips = todoTagList,
            onChipClick = { selectedChip ->
                selectedTag = selectedChip.tag
                todoTagList = todoTagList.map { chip ->
                    chip.copy(selected = chip.tag == selectedTag)
                }
            },
            onAddClick = {
                showAddTagDialog = true
            }
        )

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
            items(filteredTodoNotes.size) {
                val colors = listOf(CustomOrange, CustomYellow, CustomGreen, CustomBlue, CustomLightYellow)
                val color = colors.random()
                NoteTemplate(
                    filteredTodoNotes[it],
                    color = color,
                    onDelete = {
                        viewModel.deleteTodoNote(filteredTodoNotes[it])
                        viewModel.deleteCheckBoxesById(filteredTodoNotes[it].id)
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
                        if (tag == ""){
                            Toast.makeText(context, "Field must be filled", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.saveTags(Tag(tag = tag))
                                showAddTagDialog = false
                            }
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
            var tag by remember { mutableStateOf("All") }
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
                        if (todoTagList[it].selected){
                            tag = todoTagList[it].tag
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(5.dp)
                                .clip(RoundedCornerShape(5.dp))
                                .background(CustomBlue)
                                .clickable {
                                    todoTagList = todoTagList.map {
                                        it.copy(selected = it.tag == tag)
                                    }
                                    tag = todoTagList[it].tag
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
                        if (text.isEmpty() || tag.isEmpty() || title.isEmpty()){
                            Toast.makeText(context, "Fields must be filled", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.saveTodoNote(
                                    TodoNote(
                                        title = title,
                                        tag = tag,
                                        text = text
                                    )
                                )
                                showDailog = false
                            }
                        }

                    }
                ) {
                    Text("Save")
                }

            }

        }
    }

}