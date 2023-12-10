package com.example.bookstoremanageruser.domain.usecase

import com.example.bookstoremanageruser.domain.repo.BookRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UndoUseCase  @Inject constructor(private val bookRepo: BookRepo) {
    operator fun invoke(): Flow<Boolean> = flow{
        try {
            emit(bookRepo.undoSaveBook())
        }catch (e:Exception){
            emit(false)
        }
    }
}