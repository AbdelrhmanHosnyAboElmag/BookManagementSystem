package com.example.bookstoremanageruser.desginpattern.factory

import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.BookType
import com.example.bookstoremanageruser.data.local.entity.Reservation

class HorrorBookFactory: com.example.bookstoremanageruser.desginpattern.factory.BookFactory {
    override fun createBook(
        id: Long,
        title: String,
        image: Int,
        rate: Float,
        author: String,
        publishDate: String,
        reservation: Reservation
    ): BookEntity {
        return BookEntity(id,title, image, BookType.HORROR, rate, author, publishDate, reservation)
    }
}