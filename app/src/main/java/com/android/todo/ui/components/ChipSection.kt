package com.android.todo.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Chip(
    val text: String,
    val selected: Boolean,
    val count: Int
)


@Composable
fun ChipSection(
    chips: List<Chip>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        repeat(chips.size) {
            val offsetY by animateDpAsState(if (chips[it].selected) (-4).dp else 0.dp)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(4.dp)
                    .offset(y = offsetY)
                    .border(1.dp, if(chips[it].selected) Color.White else MaterialTheme.colorScheme.inverseSurface, RoundedCornerShape(50))
                    .padding(10.dp)
            ) {
                Text(text = chips[it].text, color = if(chips[it].selected) Color.White else MaterialTheme.colorScheme.inverseSurface)
                if (chips[it].selected) {
                    Spacer(Modifier.width(10.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(22.dp)
                            .width(22.dp)
                            .clip(RoundedCornerShape(50))
                            .background(MaterialTheme.colorScheme.inverseSurface)
                    ) {
                        Text(
                            color = Color.White,
                            fontSize = 10.sp,
                            text = chips[it].count.toString()
                        )
                    }
                }

            }
        }
    }
}