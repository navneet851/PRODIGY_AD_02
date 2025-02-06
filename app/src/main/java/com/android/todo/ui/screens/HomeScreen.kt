package com.android.todo.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.todo.ui.components.NoteTemplate

@Preview()
@Composable
private fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
       Text(
           modifier = Modifier.padding(30.dp),
           fontSize = 30.sp,
           text = "My Notes",
           fontWeight = FontWeight.Bold
       )
       LazyVerticalGrid(
           columns = GridCells.Fixed(2)
       ) {
           items(4) {
               NoteTemplate()
           }

       }

    }
}