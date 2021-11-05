package fr.devoid.montodo.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.devoid.montodo.data.AppTodoRepository
import fr.devoid.montodo.data.TodoRepository

@Module
@InstallIn(SingletonComponent::class)
interface BindingAppModule {

    @Binds
    fun bindTodoRepository(appTodoRepository: AppTodoRepository): TodoRepository

}