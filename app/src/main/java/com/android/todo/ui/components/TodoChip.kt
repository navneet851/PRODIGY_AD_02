package com.android.todo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.todo.R
import com.android.todo.data.entity.CheckBox
import com.android.todo.ui.theme.CustomBlue
import com.android.todo.ui.theme.CustomOrange

@Composable
fun TodoChip(
    color : Color,
    checkBox: CheckBox,
    onChange : (Boolean) -> Unit,
    onDelete : () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Checkbox(
                checked = checkBox.isChecked,
                onCheckedChange = {
                    onChange(it)
                },
                colors = CheckboxDefaults.colors(
                   checkmarkColor = color,
                    checkedColor = Color.Black,
                    uncheckedColor = Color.Black
                )

            )
            Text(
                text = checkBox.text,
                textDecoration = if (checkBox.isChecked) TextDecoration.LineThrough else TextDecoration.None,
            )
        }

        ShadowButton(
            modifier = Modifier
                .size(35.dp),
            image = rememberVectorPainter(Icons.Default.Delete),
            contentDescription = "Delete"
        ) {
            onDelete()
        }
    }
}