package fr.devoid.montodo.data.local

import androidx.room.*
import fr.devoid.montodo.data.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoItemDao {

    @Update
    suspend fun updateTodo(todoItem: TodoItem)

    @Delete
    suspend fun deleteTodo(todoItem: TodoItem)

    @Insert
    suspend fun insertTodo(todoItem: TodoItem)

    @Query("SELECT * FROM todo_table")
    fun getTodoItemsFlow(): Flow<List<TodoItem>>

}