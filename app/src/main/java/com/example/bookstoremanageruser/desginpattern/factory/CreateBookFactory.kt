package com.example.bookstoremanageruser.desginpattern.factory

import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.BookType

object CreateBookFactory {
    fun createBookFactory(book: BookEntity):BookEntity {
       return when (book.bookType) {
            BookType.ADVENTURE -> {
                com.example.bookstoremanageruser.desginpattern.factory.AdventureBookFactory().createBook(
                    book.id,
                    book.title,
                    book.image,
                    book.rate,
                    book.author,
                    book.publishDate,
                    book.reservation
                )
            }

            BookType.ACTION -> {
                com.example.bookstoremanageruser.desginpattern.factory.ActionBookFactory().createBook(
                    book.id,
                    book.title,
                    book.image,
                    book.rate,
                    book.author,
                    book.publishDate,
                    book.reservation
                )
            }

            BookType.HORROR -> {
                com.example.bookstoremanageruser.desginpattern.factory.HorrorBookFactory().createBook(
                    book.id,
                    book.title,
                    book.image,
                    book.rate,
                    book.author,
                    book.publishDate,
                    book.reservation
                )
            }

            BookType.SCIENTIFIC -> {
                com.example.bookstoremanageruser.desginpattern.factory.ScientificBookFactory().createBook(
                    book.id,
                    book.title,
                    book.image,
                    book.rate,
                    book.author,
                    book.publishDate,
                    book.reservation
                )
            }

            BookType.FICTION -> {
                com.example.bookstoremanageruser.desginpattern.factory.FictionBookFactory().createBook(
                    book.id,
                    book.title,
                    book.image,
                    book.rate,
                    book.author,
                    book.publishDate,
                    book.reservation
                )
            }
        }
    }
}