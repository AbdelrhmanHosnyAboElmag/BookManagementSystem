package com.example.bookstoremanageruser.desginpattern.decorator

import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity

abstract class BookDecorator(private val componentBook: com.example.bookstoremanageruser.desginpattern.decorator.ComponentBook):
    com.example.bookstoremanageruser.desginpattern.decorator.ComponentBook {
    override fun optionalReservedNoteEntity(): BookReservedEntity {
       return componentBook.optionalReservedNoteEntity()
    }
}