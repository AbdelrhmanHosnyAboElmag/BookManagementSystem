package com.example.bookstoremanageruser.desginpattern.factory

import androidx.annotation.DrawableRes
import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.Reservation

interface BookFactory {
    fun createBook(
        id: Long, title: String,
        @DrawableRes image: Int,
        rate: Float,
        author: String,
        publishDate: String,
        reservation: Reservation
    ): BookEntity
}