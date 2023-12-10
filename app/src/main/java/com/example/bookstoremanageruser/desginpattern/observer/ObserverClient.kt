package com.example.bookstoremanageruser.desginpattern.observer

import android.content.Context
import com.example.bookstoremanageruser.data.local.entity.BookType
//use to make it work in all application level ,can change to database
object ObserverClient {
    private val bookPublisher=
        com.example.bookstoremanageruser.desginpattern.observer.BookPublisher()
    fun addGenreSubscriber(bookType: BookType,context:Context){
        bookPublisher.addGenreSubscriber(bookType,
            com.example.bookstoremanageruser.desginpattern.observer.BookSubscriber(context)
        )
    }

    fun notifyGenreSubscriber(bookType: BookType){
        bookPublisher.notifyGenreSubscriber(bookType.name)
    }
}