package com.android.todo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.todo.data.db.CheckBoxDao
import com.android.todo.data.db.TagDao
import com.android.todo.data.db.TodoDao
import com.android.todo.data.entity.CheckBox
import com.android.todo.data.entity.Tag
import com.android.todo.data.entity.TodoNote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoDao: TodoDao,
    private val checkBoxDao: CheckBoxDao,
    private val tagDao: TagDao
) : ViewModel() {

    private val _todosList: MutableStateFlow<List<TodoNote>> = MutableStateFlow(emptyList())
    val todosList: StateFlow<List<TodoNote>> = _todosList

    private val _checkBoxList: MutableStateFlow<List<CheckBox>> = MutableStateFlow(emptyList())
    val checkBoxList: StateFlow<List<CheckBox>> = _checkBoxList

    private val _tagList : MutableStateFlow<List<Tag>> = MutableStateFlow(emptyList())
    val tagList : StateFlow<List<Tag>> = _tagList

    init {
        viewModelScope.launch {
            todoDao.getNotesOrderByLatest().collect {
                _todosList.value = it
            }
        }
        viewModelScope.launch {
            tagDao.getTags().collect{
                _tagList.value = it
            }
        }

    }

    fun getCheckBoxOrderByLatest(todoId : Int) {
        viewModelScope.launch {
            checkBoxDao.getCheckBoxOrderByLatest(todoId).collect {
                _checkBoxList.value = it
            }
        }
    }

    fun saveTags(tag : Tag){
        viewModelScope.launch {
            tagDao.saveTag(tag)
        }
    }

    fun deleteTags(tag : Tag){
        viewModelScope.launch {
            tagDao.deleteTag(tag)
        }
    }

    fun deleteCheckBoxesById(todoId: Int) {
        viewModelScope.launch {
            checkBoxDao.deleteCheckBoxesById(todoId)
        }
    }

    fun deleteCheckBox(checkBox: CheckBox) {
        viewModelScope.launch {
            checkBoxDao.deleteCheckBox(checkBox)
        }
    }

    fun saveCheckBox(checkBox: CheckBox) {
        viewModelScope.launch {
            checkBoxDao.upsertCheckBox(checkBox)
        }
    }


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