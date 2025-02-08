package com.android.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Shapes
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.todo.ui.theme.CustomOrange
import com.android.todo.ui.theme.shaded

@Preview
@Composable
fun NoteTextField(

) {
    var text by remember { mutableStateOf("navbar") }
    TextField(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(20))
        ,
        value = text,
        onValueChange = {
            text = it
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = shaded,
            unfocusedContainerColor = shaded,
            disabledContainerColor = shaded,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedTextColor = Color.Black,
            focusedTextColor = Color.Black,
            disabledTextColor = Color.Black,
            cursorColor = Color.Black,
            selectionColors = TextSelectionColors(
                handleColor = Color.Black,
                backgroundColor = shaded
            )
        )
    )
}