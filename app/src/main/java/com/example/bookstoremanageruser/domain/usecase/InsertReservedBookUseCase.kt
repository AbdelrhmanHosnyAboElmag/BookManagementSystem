package com.example.bookstoremanageruser.domain.usecase

import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity
import com.example.bookstoremanageruser.domain.repo.BookRepo
import com.example.bookstoremanageruser.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InsertReservedBookUseCase@Inject constructor(private val bookRepo: BookRepo) {
    operator fun invoke(bookEntity: BookReservedEntity): Flow<DataResult<Boolean>> = flow{
        try {
            emit(DataResult.Success(bookRepo.insertReservedBook(bookEntity)))
        }catch (e:Exception){
            emit(DataResult.Error(e.message.toString()))
        }
    }
}