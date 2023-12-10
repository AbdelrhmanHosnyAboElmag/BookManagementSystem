package com.example.bookstoremanageruser.domain.usecase

import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity
import com.example.bookstoremanageruser.domain.repo.BookRepo
import com.example.bookstoremanageruser.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetReservedBookEntity @Inject constructor(private val bookRepo: BookRepo) {
    operator fun invoke(): Flow<DataResult<List<BookReservedEntity>>> = flow{
        try {
            emit(DataResult.Success(bookRepo.getBookReservedList()))
        }catch (e:Exception){
            emit(DataResult.Error(e.message.toString()))
        }
    }
}