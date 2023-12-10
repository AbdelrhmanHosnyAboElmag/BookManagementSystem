package com.example.bookstoremanageruser.desginpattern.strategy

import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.SortBy

object UtilSortAlgorithme {
    fun sortByField(list: List<BookEntity>,sortBy: SortBy): List<BookEntity> {
        return when (sortBy) {
            SortBy.TITLE -> list.sortedBy { it.title }
            SortBy.PUBLISH_DATE -> list.sortedBy { it.publishDate }
            SortBy.AUTHOR -> list.sortedBy { it.author }
        }
    }
}