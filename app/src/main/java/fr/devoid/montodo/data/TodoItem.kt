package fr.devoid.montodo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Une simple class qui transportera des données d'un TodoItem
@Entity(tableName = "todo_table")
data class TodoItem(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    val text: String,
    val createdAt: Long,
    val doneAt: Long?
)
