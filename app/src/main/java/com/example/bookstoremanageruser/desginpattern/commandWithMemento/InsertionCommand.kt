package com.example.bookstoremanageruser.desginpattern.commandWithMemento


interface InsertionCommand {
    fun execute():Boolean
    fun getId():Long
}