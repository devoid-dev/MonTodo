package fr.devoid.montodo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.devoid.montodo.data.TodoItem

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun getTodoItemDao(): TodoItemDao
}