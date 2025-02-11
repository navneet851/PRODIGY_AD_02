package com.android.todo.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.todo.data.db.CheckBoxDao
import com.android.todo.data.db.TodoDao
import com.android.todo.data.entity.TodoNote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoDao: TodoDao,
    private val checkBoxDao: CheckBoxDao
    ) : ViewModel() {

    private val _todosList: MutableStateFlow<List<TodoNote>> = MutableStateFlow(emptyList())
    val todosList: StateFlow<List<TodoNote>> = _todosList

    init {
        viewModelScope.launch {
            todoDao.getNotesOrderByLatest().collect {
                _todosList.value = it
            }
        }

    }

    private val titleState: MutableState<String> = mutableStateOf("")
    private val tagState: MutableState<String> = mutableStateOf("")
    private val textState: MutableState<String> = mutableStateOf("")


    fun deleteTodoNote(todoNote: TodoNote) {
        viewModelScope.launch {
            todoDao.deleteTodoNote(todoNote)
        }
    }

    fun saveTodoNote(todoNote: TodoNote) {
        viewModelScope.launch {
            todoDao.upsertTodoNote(todoNote)
        }
    }
}