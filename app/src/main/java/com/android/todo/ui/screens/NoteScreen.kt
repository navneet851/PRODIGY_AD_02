package com.android.todo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.todo.R
import com.android.todo.ui.components.NoteTextField
import com.android.todo.ui.components.ShadowButton
import com.android.todo.ui.components.TodoChip
import com.android.todo.ui.theme.shaded

@Preview
@Composable
fun NoteScreen() {

    Scaffold(
        modifier = Modifier
            .background(Color(0xFFEA7A53))
            .padding(0.dp, 16.dp),
        containerColor = Color(0xFFEA7A53),
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
                .background(Color(0xFFEA7A53))
                .padding(16.dp, 0.dp)
                .verticalScroll(rememberScrollState())
        ) {


            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                modifier = Modifier.padding(16.dp),
                fontSize = 50.sp,
                text = "Title",
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