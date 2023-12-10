package com.example.bookstoremanageruser.data.local.repo

import com.example.bookstoremanageruser.data.local.database.BookDatabase
import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity
import com.example.bookstoremanageruser.desginpattern.commandWithMemento.DataBaseCommandInvoker
import com.example.bookstoremanageruser.desginpattern.commandWithMemento.SaveToDataBaseInsertionCommand
import com.example.bookstoremanageruser.domain.repo.BookRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookRepoImpl @Inject constructor(
    private val database: BookDatabase,
    private val databaseCommandInvoker: DataBaseCommandInvoker
) : BookRepo {
    override suspend fun getBookList(): List<BookEntity> {
        var bookList: List<BookEntity>
        withContext(Dispatchers.IO) {
            bookList = database.BookDao.getBooks()
        }
        return bookList
    }

    override suspend fun getBookReservedList(): List<BookReservedEntity> {
        var bookReservedList: List<BookReservedEntity>
        withContext(Dispatchers.IO) {
            bookReservedList = database.BookReservedDao.getReservedBook()
        }
        return bookReservedList
    }

    override suspend fun insertReservedBook(bookReservedEntity: BookReservedEntity): Boolean {
        var isSuccess = false
        withContext(Dispatchers.IO) {
            isSuccess = databaseCommandInvoker.executeCommand(
                SaveToDataBaseInsertionCommand(
                    database,
                    bookReservedEntity
                ), bookReservedEntity
            )
        }
        return isSuccess
    }


    override suspend fun insertBook(bookEntity: BookEntity): Boolean {
        var isSuccess = false
        withContext(Dispatchers.IO) {
            if (database.BookDao.insertAll(bookEntity).isNotEmpty()) {
                isSuccess = true
            }
        }
        return isSuccess
    }

    override suspend fun removeBook(id: Long): Int {
        var rowDeleted = 0
        withContext(Dispatchers.IO) {
            rowDeleted = database.BookReservedDao.deleteById(id)
        }
        return rowDeleted
    }


    override suspend fun undoSaveBook():Boolean {
        var isSuccess = false
        withContext(Dispatchers.IO){
           isSuccess = databaseCommandInvoker.undo(bookReservedDao = database.BookReservedDao)
        }
        return isSuccess
    }

    override suspend fun redoSaveBook(): BookReservedEntity {
        return databaseCommandInvoker.redo()
    }
}