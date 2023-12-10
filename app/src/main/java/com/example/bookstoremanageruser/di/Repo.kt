package com.example.bookstoremanageruser.di

import com.example.bookstoremanageruser.data.local.repo.BookRepoImpl
import com.example.bookstoremanageruser.domain.repo.BookRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Repo {
    @Provides
    @Singleton
    fun provideRepositoriesItemRepository(repository: BookRepoImpl): BookRepo {
        return repository
    }
}