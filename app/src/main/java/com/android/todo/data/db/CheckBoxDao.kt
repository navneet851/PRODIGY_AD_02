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

    @Query("SELECT * FROM CheckBox ORDER BY id ASC")
    fun getCheckBoxOrderByLatest() : Flow<List<CheckBox>>
}