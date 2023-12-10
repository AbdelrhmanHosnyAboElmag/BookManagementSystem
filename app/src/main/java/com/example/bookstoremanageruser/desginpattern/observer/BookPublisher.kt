package com.example.bookstoremanageruser.desginpattern.observer

import com.example.bookstoremanageruser.data.local.entity.BookType

class BookPublisher  {
    private val eventManager: com.example.bookstoremanageruser.desginpattern.observer.EventManager =
        com.example.bookstoremanageruser.desginpattern.observer.EventManager()

    fun addAuthorSubscriber(bookType: BookType,eventBookListener: com.example.bookstoremanageruser.desginpattern.observer.EventBookListener){
        eventManager.subscribe(bookType.name,eventBookListener)
    }

    fun removeAuthorSubscriber(bookType: BookType,eventBookListener: com.example.bookstoremanageruser.desginpattern.observer.EventBookListener){
        eventManager.unsubscribe(bookType.name,eventBookListener)
    }

    fun addGenreSubscriber(bookType: BookType,eventBookListener: com.example.bookstoremanageruser.desginpattern.observer.EventBookListener){
        eventManager.subscribe(bookType.name,eventBookListener)
    }
    fun removeGenreSubscriber(bookType: BookType,eventBookListener: com.example.bookstoremanageruser.desginpattern.observer.EventBookListener){
        eventManager.unsubscribe(bookType.name,eventBookListener)
    }

    fun notifyGenreSubscriber(eventType:String){
        eventManager.notify(eventType,"hi there new book add you subscribe for $eventType :)")
    }
}