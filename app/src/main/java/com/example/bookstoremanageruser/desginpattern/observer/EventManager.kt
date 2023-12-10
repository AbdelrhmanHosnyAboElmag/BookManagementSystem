package com.example.bookstoremanageruser.desginpattern.observer

class EventManager {
    private val listeners: MutableMap<String, MutableList<com.example.bookstoremanageruser.desginpattern.observer.EventBookListener>> = mutableMapOf()

    fun subscribe(eventType: String, listener: com.example.bookstoremanageruser.desginpattern.observer.EventBookListener) {
        listeners.computeIfAbsent(eventType) { mutableListOf() }.add(listener)
    }

    fun unsubscribe(eventType: String, listener: com.example.bookstoremanageruser.desginpattern.observer.EventBookListener) {
        listeners[eventType]?.remove(listener)
    }

    fun notify(eventType: String, data: Any) {
        listeners[eventType]?.forEach { it.notify(data) }
    }
}