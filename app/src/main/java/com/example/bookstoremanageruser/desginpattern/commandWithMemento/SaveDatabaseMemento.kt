package com.example.bookstoremanageruser.desginpattern.commandWithMemento

import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity

class SaveDatabaseMemento(private val savedBooks: BookReservedEntity) {
    fun getSavedBook():BookReservedEntity = savedBooks
}