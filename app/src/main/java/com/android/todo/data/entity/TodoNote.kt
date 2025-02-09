package com.android.todo.data.entity

import androidx.compose.ui.graphics.Color
import com.android.todo.ui.theme.CustomOrange
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class TodoNote(
    val id : Int,
    val title : String,
    val tag : String,
//    val todo : List<TodoCheckBox>,
//    @Contextual val color : Color
)