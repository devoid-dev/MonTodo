package fr.devoid.montodo.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.devoid.montodo.data.local.TodoDatabase
import fr.devoid.montodo.data.local.TodoItemDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(context, TodoDatabase::class.java, "tododatabase.db").build()
    }

    @Provides
    @Singleton
    fun provideTodoItemDao(todoDatabase: TodoDatabase): TodoItemDao {
        return todoDatabase.getTodoItemDao()
    }

}