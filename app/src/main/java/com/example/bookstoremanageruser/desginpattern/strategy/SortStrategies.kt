package com.example.bookstoremanageruser.desginpattern.strategy

import com.example.bookstoremanageruser.data.local.entity.BookEntity

interface SortStrategies {
    fun execute(listBookEntity:List<BookEntity>): List<BookEntity>
}
