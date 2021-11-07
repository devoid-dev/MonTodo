package fr.devoid.montodo.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.devoid.montodo.data.AppTodoRepository
import fr.devoid.montodo.data.TodoRepository

//Une classe qui indique à Hilt quelle implémentation fournir pour telle interface.
@Module
@InstallIn(SingletonComponent::class)
interface BindingAppModule {

    @Binds
    fun bindTodoRepository(appTodoRepository: AppTodoRepository): TodoRepository

}