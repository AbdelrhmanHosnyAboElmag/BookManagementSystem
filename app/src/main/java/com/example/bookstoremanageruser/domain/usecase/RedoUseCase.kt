package com.example.bookstoremanageruser.domain.usecase

import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity
import com.example.bookstoremanageruser.domain.repo.BookRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RedoUseCase @Inject constructor(private val bookRepo: BookRepo) {
    operator fun invoke(): Flow<BookReservedEntity> = flow {
        try {
            emit(bookRepo.redoSaveBook())
        } catch (e: Exception) {
            emit(BookReservedEntity())
        }
    }
}