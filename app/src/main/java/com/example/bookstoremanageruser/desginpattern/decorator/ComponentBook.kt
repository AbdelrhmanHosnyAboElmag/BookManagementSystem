package com.example.bookstoremanageruser.desginpattern.decorator

import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity


interface ComponentBook {
    fun optionalReservedNoteEntity():BookReservedEntity
}