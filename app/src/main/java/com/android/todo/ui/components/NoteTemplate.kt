package com.android.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.todo.data.entity.TodoNote
import com.android.todo.ui.theme.CustomOrange

@Composable
fun NoteTemplate(
    todoNote: TodoNote,
    color : Color = CustomOrange,
    onDelete : () -> Unit,
    onClick : () -> Unit
) {
    Column(
        modifier = Modifier
            .height(280.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(color)
            .padding(16.dp)
            .clickable {
                onClick()
            }

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .width(80.dp)
//                    .padding(10.dp)
                    ,
                fontSize = 20.sp,
                text = todoNote.title.uppercase(),
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.W500
            )

            ShadowButton(
                image = rememberVectorPainter(Icons.Default.Delete),
                contentDescription = "Star"
            ) {
                onDelete()
            }
        }

        Text(
            fontSize = 20.sp,
            color = Color.Black,
            text = todoNote.text)
    }

}

@Preview
@Composable
private fun NoteTemplatePreview() {
    val todo = TodoNote(title = "navbar", tag = "imp", text = "design gerfcvfrcgrgtrggcccccccctggtrrrrrrrgttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttthe navbar")
    NoteTemplate(todo , onDelete = {}) { }
}