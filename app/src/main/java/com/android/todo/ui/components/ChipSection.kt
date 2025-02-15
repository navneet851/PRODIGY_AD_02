package com.android.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.todo.data.entity.Chip


@Composable
fun ChipSection(
    chips: List<Chip>,
    onClick: () -> Unit
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
    ) {
        item{
            IconButton(
                modifier = Modifier
                    .padding(5.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(50))
                ,
                onClick = {
                    onClick()
                }
            ) {
                Icon(
                    Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = "Add"
                )
            }
        }
        items(chips.size) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(4.dp)
                    .border(1.dp, if(chips[it].selected) Color.White else Color.DarkGray, RoundedCornerShape(50))
                    .padding(12.dp, 10.dp)
            ) {
                Text(text = chips[it].tag, color = if(chips[it].selected) Color.White else Color.DarkGray)
                if (chips[it].selected) {
                    Spacer(Modifier.width(10.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(22.dp)
                            .width(22.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color.DarkGray)
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