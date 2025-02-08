package com.android.todo.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.todo.ui.theme.shaded

@Composable
fun ShadowButton(
    modifier: Modifier = Modifier,
    image : Painter,
    contentDescription : String,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
        ,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = shaded
        ),
        onClick = onClick
    ) {
        Icon(
            image,
            tint = Color.Black,
            contentDescription = contentDescription
        )
    }
}