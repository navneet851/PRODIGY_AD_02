package com.android.todo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val tag : String
)