package com.example.bookstoremanageruser.domain.repo

import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity

interface BookRepo {
    suspend fun getBookList():List<BookEntity>
    suspend fun getBookReservedList():List<BookReservedEntity>
    suspend fun insertReservedBook(bookReservedEntity: BookReservedEntity):Boolean
    suspend fun insertBook(bookEntity: BookEntity):Boolean
    suspend fun removeBook(id:Long):Int
    suspend fun undoSaveBook(): Boolean
    suspend fun redoSaveBook():BookReservedEntity
}