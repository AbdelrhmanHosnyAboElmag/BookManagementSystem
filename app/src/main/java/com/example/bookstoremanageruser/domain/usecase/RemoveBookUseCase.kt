package com.example.bookstoremanageruser.domain.usecase

import com.example.bookstoremanageruser.domain.repo.BookRepo
import com.example.bookstoremanageruser.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveBookUseCase @Inject constructor(private val bookRepo: BookRepo) {
    operator fun invoke(id:Long): Flow<DataResult<Int>> = flow{
        try {
            emit(DataResult.Success(bookRepo.removeBook(id)))
        }catch (e:Exception){
            emit(DataResult.Error(e.message.toString()))
        }
    }
}