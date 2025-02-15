package com.android.todo.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.android.todo.data.entity.CheckBox
import com.android.todo.data.entity.TodoNote
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckBoxDao {

    @Upsert
    suspend fun upsertCheckBox(checkBox: CheckBox)

    @Delete
    suspend fun deleteCheckBox(checkBox: CheckBox)

    @Query("SELECT * FROM CheckBox Where todoId = :todoId ORDER BY id ASC")
    fun getCheckBoxOrderByLatest(todoId : Int) : Flow<List<CheckBox>>

    @Query("DELETE FROM CheckBox WHERE todoId = :todoId")
    suspend fun deleteCheckBoxesById(todoId: Int)
}