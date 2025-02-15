package com.android.todo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CheckBox(
    val todoId: Int,
    val text: String,
    val isChecked: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)