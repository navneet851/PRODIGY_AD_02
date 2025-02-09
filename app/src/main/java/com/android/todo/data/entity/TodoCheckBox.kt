package com.android.todo.data.entity

import kotlinx.serialization.Serializable

@Serializable
class TodoCheckBox(
    val text : String,
    val isChecked : Boolean
)
