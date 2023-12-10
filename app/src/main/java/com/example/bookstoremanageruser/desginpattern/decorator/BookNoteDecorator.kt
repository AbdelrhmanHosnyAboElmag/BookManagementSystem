package com.example.bookstoremanageruser.desginpattern.decorator

import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity


class BookNoteDecorator(componentBook: com.example.bookstoremanageruser.desginpattern.decorator.ComponentBook, private val additionalNote: String) :
    com.example.bookstoremanageruser.desginpattern.decorator.BookDecorator(componentBook) {
    override fun optionalReservedNoteEntity(): BookReservedEntity {
        return super.optionalReservedNoteEntity().copy(optionalNotes = additionalNote)
    }
}