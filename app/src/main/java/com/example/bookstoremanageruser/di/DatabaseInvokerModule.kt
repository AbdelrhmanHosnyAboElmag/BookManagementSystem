package com.example.bookstoremanageruser.di

import com.example.bookstoremanageruser.desginpattern.commandWithMemento.DataBaseCommandInvoker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseInvokerModule {
    @Provides
    @Singleton
    fun getInvoker(): DataBaseCommandInvoker {
        return DataBaseCommandInvoker()
    }
}