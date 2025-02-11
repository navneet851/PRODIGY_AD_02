package com.android.todo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CheckBox(
    @PrimaryKey()
    val id : Int,
    val text: String,
    val isChecked: Boolean
)