package com.example.bookstoremanageruser.desginpattern.commandWithMemento

import com.example.bookstoremanageruser.data.local.database.BookDatabase
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity

class SaveToDataBaseInsertionCommand constructor(
    private val database: BookDatabase,
    private val bookReservedEntity: BookReservedEntity
) : InsertionCommand {
    override fun execute():Boolean {
        return database.BookReservedDao.insertAll(bookReservedEntity).isNotEmpty()
    }

    override fun getId(): Long {
        return bookReservedEntity.id
    }
}