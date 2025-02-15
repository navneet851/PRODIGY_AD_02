package com.android.todo.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.android.todo.R
import com.android.todo.data.entity.CheckBox
import com.android.todo.data.entity.TodoNote
import com.android.todo.ui.components.DialogTextField
import com.android.todo.ui.components.NoteTextField
import com.android.todo.ui.components.ShadowButton
import com.android.todo.ui.components.TodoChip
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
fun NoteScreen(navController: NavHostController, viewModel: TodoViewModel, todoNoteIndex: Int) {

    val todoNotes by viewModel.todosList.collectAsState()
    val checkBoxList by viewModel.checkBoxList.collectAsState()
    Log.d("todoid", todoNoteIndex.toString())
    val colors = listOf(CustomOrange, CustomYellow, CustomGreen, CustomBlue, CustomLightYellow)
    val color by remember {
        mutableStateOf(colors.random())
    }

    val focusRequester = remember { FocusRequester() }

    var showDailog by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    var todoText by remember {
        mutableStateOf(todoNotes[todoNoteIndex].text)
    }
    LaunchedEffect(todoText) {
        viewModel.saveTodoNote(todoNotes[todoNoteIndex].copy(text = todoText))
    }
    LaunchedEffect(true) {
        viewModel.getCheckBoxOrderByLatest(todoNotes[todoNoteIndex].id)
//        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = Modifier
            .background(color)
            .padding(0.dp, 16.dp),
        containerColor = Color.Transparent,
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                ShadowButton(
                    image = rememberVectorPainter(Icons.AutoMirrored.Filled.ArrowBack),
                    contentDescription = "Back"
                ) {
                    navController.navigateUp()
                }

            }
        },
        bottomBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .fillMaxWidth()
            ) {
                ShadowButton(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(60.dp),
                    image = painterResource(R.drawable.text),
                    contentDescription = "text"
                ) {
                    if (todoText == ""){
                        todoText = " "
                    }
                    else{
                        focusRequester.requestFocus()
                    }

                }
                ShadowButton(
                    modifier = Modifier
                        .size(60.dp),
                    image = painterResource(R.drawable.checkbox),
                    contentDescription = "add checkbox"
                ) {
                    showDailog = true
                }
            }
        }
    ) {

        if (todoNotes.isEmpty()) {
            Text(text = "No notes found")
        } else {

            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(color)
                    .padding(16.dp, 0.dp)
                    .verticalScroll(rememberScrollState())
            ) {


                Spacer(modifier = Modifier.padding(16.dp))

                Text(
                    modifier = Modifier.padding(16.dp),
                    fontSize = 50.sp,
                    text = todoNotes[todoNoteIndex].title.uppercase(),
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.padding(16.dp))

                if (todoText != ""){
                    ShadowButton(
                        modifier = Modifier
                            .size(35.dp),
                        image = painterResource(R.drawable.text),
                        contentDescription = "text field"
                    ) { }


                    NoteTextField(todoText, focusRequester) { newText ->
                        todoText = newText
                    }
                }


                Spacer(modifier = Modifier.padding(16.dp))

                if (checkBoxList.isNotEmpty()) {
                    Row(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        ShadowButton(
                            modifier = Modifier
                                .size(35.dp),
                            image = painterResource(R.drawable.checkbox),
                            contentDescription = "checkbox list"
                        ) { }
                        Spacer(modifier = Modifier.padding(3.dp))
                        ShadowButton(
                            modifier = Modifier
                                .size(35.dp),
                            image = rememberVectorPainter(Icons.Default.Add),
                            contentDescription = "Add checkbox"
                        ) {
                            showDailog = true
                        }
                    }



                    Column(
                        modifier = Modifier
                            .padding(0.dp, 5.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10))
                            .background(shaded)
                            .padding(10.dp)
                    ) {
                        repeat(checkBoxList.size) { checkBox ->
                            TodoChip(
                                color,
                                checkBoxList[checkBox],
                                onChange = {
                                    viewModel.saveCheckBox(checkBoxList[checkBox].copy(isChecked = it))
                                },
                                onDelete = {
                                    viewModel.deleteCheckBox(checkBoxList[checkBox])
                                }
                            )
                        }
                    }
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(260.dp)
                    .background(shaded)
                    .padding(16.dp)
            ) {


                var text by remember { mutableStateOf("") }

                ShadowButton(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(60.dp),
                    image = painterResource(R.drawable.checkbox),
                    contentDescription = "checkbox"
                ) { }

                DialogTextField(
                    color = color,
                    text = text,
                    onChange = {
                        text = it
                    }
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color,
                        contentColor = Color.Black
                    ),
                    onClick = {
                        if (text == ""){
                            Toast.makeText(context, "Field must be filled", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.saveCheckBox(
                                    CheckBox(
                                        todoNotes[todoNoteIndex].id,
                                        text,
                                        false
                                    )
                                )
                                showDailog = false
                            }
                        }

                    }
                ) {
                    Text("Add")
                }

            }
        }
    }
}