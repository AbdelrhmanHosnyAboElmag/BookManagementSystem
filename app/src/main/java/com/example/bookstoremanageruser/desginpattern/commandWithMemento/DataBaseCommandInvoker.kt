package com.example.bookstoremanageruser.desginpattern.commandWithMemento

import android.util.Log
import com.example.bookstoremanageruser.data.local.dao.BookReservedDao
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity

//act as careTaker for memento pattern
class DataBaseCommandInvoker {
    private val insertionCommands = mutableListOf<InsertionCommand>()
    private val mementos: MutableList<SaveDatabaseMemento> = mutableListOf()

    fun executeCommand(
        insertionCommand: InsertionCommand,
        savedBooks: BookReservedEntity
    ): Boolean {
        insertionCommands.add(insertionCommand)
        Log.d("UndoCommand", "executeCommand:${savedBooks}")
        saveFavoriteBooksList(SaveDatabaseMemento(savedBooks).getSavedBook())
        return insertionCommand.execute()
    }

    private fun saveFavoriteBooksList(favoriteBooksList: BookReservedEntity) {
        mementos.add(SaveDatabaseMemento(favoriteBooksList))
    }

    fun undo(bookReservedDao: BookReservedDao): Boolean {
        return try {
            bookReservedDao.deleteById(mementos[mementos.size - 1].getSavedBook().id)
            insertionCommands.removeLast()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun redo(): BookReservedEntity {
        return if (mementos.isEmpty()) {
            BookReservedEntity()
        } else {
            val lastMemento = mementos.removeLast()
            lastMemento.getSavedBook()
        }
    }
}