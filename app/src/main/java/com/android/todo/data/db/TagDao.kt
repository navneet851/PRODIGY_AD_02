package com.android.todo.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.android.todo.data.entity.Tag
import kotlinx.coroutines.flow.Flow


@Dao
interface TagDao {
    @Upsert
    suspend fun saveTag(tag : Tag)

    @Delete
    suspend fun deleteTag(tag : Tag)

    @Query("SELECT * FROM Tag")
    fun getTags() : Flow<List<Tag>>

}