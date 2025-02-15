package com.android.todo.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.android.todo.data.entity.TodoNote
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Upsert
    suspend fun upsertTodoNote(todoNote: TodoNote)

    @Delete
    suspend fun deleteTodoNote(todoNote: TodoNote)

    @Query("SELECT * FROM TodoNote ORDER BY id DESC")
    fun getNotesOrderByLatest() : Flow<List<TodoNote>>
}