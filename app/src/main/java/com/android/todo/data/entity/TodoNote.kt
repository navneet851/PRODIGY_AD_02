package com.android.todo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
data class TodoNote(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val tag : String,
)