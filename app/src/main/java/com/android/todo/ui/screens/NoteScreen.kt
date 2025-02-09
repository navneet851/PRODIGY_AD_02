package com.android.todo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.todo.R
import com.android.todo.data.entity.TodoNote
import com.android.todo.ui.components.NoteTextField
import com.android.todo.ui.components.ShadowButton
import com.android.todo.ui.components.TodoChip
import com.android.todo.ui.theme.CustomBlue
import com.android.todo.ui.theme.shaded

@Composable
fun NoteScreen(navController: NavHostController, todoNote: TodoNote) {

    Scaffold(
        modifier = Modifier
            .background(CustomBlue)
            .padding(0.dp, 16.dp),
        containerColor = Color.Transparent,
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
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

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = shaded,
                        contentColor = Color.Black
                    ),
                    onClick = {

                    }
                ) {
                    Text("Save")
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
                    contentDescription = "Back"
                ) { }
                ShadowButton(
                    modifier = Modifier
                        .size(60.dp),
                    image = painterResource(R.drawable.checkbox),
                    contentDescription = "Back"
                ) { }
            }
        }
    ) {


        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(CustomBlue)
                .padding(16.dp, 0.dp)
                .verticalScroll(rememberScrollState())
        ) {


            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                modifier = Modifier.padding(16.dp),
                fontSize = 50.sp,
                text = todoNote.title,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.padding(16.dp))

            ShadowButton(
                modifier = Modifier
                    .size(35.dp),
                image = painterResource(R.drawable.text),
                contentDescription = "Back"
            ) { }
            NoteTextField()

            Spacer(modifier = Modifier.padding(16.dp))

            ShadowButton(
                modifier = Modifier
                    .size(35.dp),
                image = painterResource(R.drawable.checkbox),
                contentDescription = "Back"
            ) { }

            Column(
                modifier = Modifier
                    .padding(0.dp, 5.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10))
                    .background(shaded)
                    .padding(10.dp)
            ) {
                TodoChip()
                TodoChip()
                TodoChip()
                TodoChip()
                TodoChip()
                TodoChip()
                TodoChip()
                TodoChip()
                TodoChip()
                TodoChip()
                TodoChip()
            }


        }


    }

}