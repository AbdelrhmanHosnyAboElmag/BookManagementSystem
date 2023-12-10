package com.example.bookstoremanageruser.desginpattern.commandWithMemento

import com.example.bookstoremanageruser.data.local.database.BookDatabase
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity

//class CancellationInserationToDataBase constructor(private val database: BookDatabase, private val bookReservedEntity: BookReservedEntity):
//    InsertionCommand {
//    override fun execute():Boolean {
//       return database.BookReservedDao.deleteById(bookReservedEntity.id) != 0
//    }
//}