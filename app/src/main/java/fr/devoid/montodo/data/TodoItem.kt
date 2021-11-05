package fr.devoid.montodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoItem(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val text: String,
    val createdAt: Long,
    val doneAt: Long?
)
