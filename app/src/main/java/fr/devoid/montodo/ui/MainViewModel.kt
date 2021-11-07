package fr.devoid.montodo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.devoid.montodo.data.TodoItem
import fr.devoid.montodo.data.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val todoItemsFlow = todoRepository.getTodoItemsFlow()

    fun onTodoItemCheck(todoItem: TodoItem) = viewModelScope.launch(Dispatchers.IO) {
        val isTodoDone = todoItem.doneAt != null

        if (isTodoDone) {
            val newTodoItem = todoItem.copy(doneAt = null)
            todoRepository.updateTodo(newTodoItem)
        } else {
            val newTodoItem = todoItem.copy(doneAt = System.currentTimeMillis())
            todoRepository.updateTodo(newTodoItem)
        }
    }

    fun onTodoDelete(todoItem: TodoItem) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.deleteTodo(todoItem)
    }

    fun onAddTodoItem(taskText: String) {
        val cleanTaskText = taskText.trim()

        if (cleanTaskText != "") {
            viewModelScope.launch(Dispatchers.IO) {
                val newTodoItem = TodoItem(
                    null,
                    cleanTaskText,
                    System.currentTimeMillis(),
                    null
                )

                todoRepository.insertTodo(newTodoItem)
            }
        }
    }

}