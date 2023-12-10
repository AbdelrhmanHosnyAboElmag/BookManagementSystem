package com.example.bookstoremanageruser.desginpattern.strategy

import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.SortBy

class StrategySortPublishDate:SortStrategies {
    override fun execute(listBookEntity: List<BookEntity>): List<BookEntity> {
        return UtilSortAlgorithme.sortByField(listBookEntity, SortBy.PUBLISH_DATE)
    }
}