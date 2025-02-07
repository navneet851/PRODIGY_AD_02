package com.android.todo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.todo.ui.components.Chip
import com.android.todo.ui.components.ChipSection
import com.android.todo.ui.components.NoteTemplate
import com.android.todo.ui.theme.CustomBlue
import com.android.todo.ui.theme.CustomGreen
import com.android.todo.ui.theme.CustomLightYellow
import com.android.todo.ui.theme.CustomOrange
import com.android.todo.ui.theme.CustomYellow

@Preview()
@Composable
private fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        var chips = listOf(
            Chip("All", true, 20),
            Chip("Work", false, 15),
            Chip("Personal", false, 34)
        )

        val colors = listOf(CustomOrange, CustomYellow, CustomGreen, CustomBlue, CustomLightYellow)
        Spacer(Modifier.padding(16.dp))
        Text(
            modifier = Modifier.padding(16.dp),
            fontSize = 50.sp,
            text = "My \nNotes",
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.padding(16.dp))


        ChipSection(chips)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(colors.size) {
                NoteTemplate(color = colors[it])
            }

        }

    }
}