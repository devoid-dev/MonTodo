package fr.devoid.montodo.data

import fr.devoid.montodo.data.local.TodoItemDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppTodoRepository @Inject constructor(
    private val todoItemDao: TodoItemDao
) : TodoRepository {

    override suspend fun insertTodo(todoItem: TodoItem) {
        todoItemDao.insertTodo(todoItem)
    }

    override suspend fun deleteTodo(todoItem: TodoItem) {
        todoItemDao.deleteTodo(todoItem)
    }

    override suspend fun updateTodo(todoItem: TodoItem) {
        todoItemDao.updateTodo(todoItem)
    }

    override fun getTodoItemsFlow(): Flow<List<TodoItem>> {
        return todoItemDao.getTodoItemsFlow()
    }

}