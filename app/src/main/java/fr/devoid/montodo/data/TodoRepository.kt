package fr.devoid.montodo.data

import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun insertTodo(todoItem: TodoItem)
    suspend fun deleteTodo(todoItem: TodoItem)
    suspend fun updateTodo(todoItem: TodoItem)
    fun getTodoItemsFlow(): Flow<List<TodoItem>>
}