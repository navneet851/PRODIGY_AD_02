package com.android.todo.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.todo.ui.theme.CustomBlue
import com.android.todo.ui.theme.CustomOrange
import com.android.todo.ui.theme.shaded

@Preview
@Composable
fun DialogTextField(
    color : Color = CustomBlue,
    text : String = "vddvd",
    placeholder: String = "Enter text",
    onChange : (String) -> Unit = {}
) {
    TextField(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(20))
        ,
        value = text,
        onValueChange = {
            onChange(it)
        },
        enabled = true,
        placeholder = {
            Text(
                text = placeholder,
                color = Color.DarkGray
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = color,
            unfocusedContainerColor = color,
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