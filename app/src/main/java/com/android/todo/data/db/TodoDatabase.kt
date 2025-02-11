package com.android.todo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.todo.data.entity.CheckBox
import com.android.todo.data.entity.TodoNote

@Database(
    entities = [TodoNote::class, CheckBox::class],
    version = 1
)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao
    abstract val checkBoxDao: CheckBoxDao

}