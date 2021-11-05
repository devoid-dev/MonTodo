package fr.devoid.montodo.data

import fr.devoid.montodo.data.local.TodoItemDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface TodoRepository {
    suspend fun insertTodo(todoItem: TodoItem)
    suspend fun deleteTodo(todoItem: TodoItem)
    suspend fun updateTodo(todoItem: TodoItem)
    fun getTodoItemsFlow(): Flow<List<TodoItem>>
}