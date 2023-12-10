package com.example.bookstoremanageruser.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookstoremanageruser.data.local.dao.BookDao
import com.example.bookstoremanageruser.data.local.dao.BookReservedDao
import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity
import com.example.bookstoremanageruser.utils.BookTypeConvertor
import com.example.bookstoremanageruser.utils.ReservationConvertor

@Database(entities =[BookEntity::class,BookReservedEntity::class], version = 1)
@TypeConverters(BookTypeConvertor::class,ReservationConvertor::class)
abstract class BookDatabase:RoomDatabase() {
    abstract val BookDao: BookDao
    abstract val BookReservedDao: BookReservedDao
}