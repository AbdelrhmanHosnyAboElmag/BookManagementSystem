package com.example.bookstoremanageruser.desginpattern.strategy

import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.SortBy
import com.example.bookstoremanageruser.desginpattern.strategy.UtilSortAlgorithme.sortByField

class StrategySortTitle:SortStrategies {
    override fun execute(listBookEntity:List<BookEntity>): List<BookEntity> {
       return sortByField(listBookEntity,SortBy.TITLE)
    }
}