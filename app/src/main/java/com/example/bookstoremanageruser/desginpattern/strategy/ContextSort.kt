package com.example.bookstoremanageruser.desginpattern.strategy

import com.example.bookstoremanageruser.data.local.entity.BookEntity

class ContextSort {
    private lateinit var sortStrategies: SortStrategies

    fun setStrategy(sortStrategies: SortStrategies) {
        this.sortStrategies = sortStrategies
    }

    fun executeStrategy(listBookEntity: List<BookEntity>): List<BookEntity> {
        return sortStrategies.execute(listBookEntity = listBookEntity)
    }
}