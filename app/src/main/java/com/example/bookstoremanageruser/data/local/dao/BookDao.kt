package com.example.bookstoremanageruser.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookstoremanageruser.data.local.entity.BookEntity

@Dao
interface BookDao {
    @Query("select * from bookentity")
    fun getBooks(): MutableList<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg books: BookEntity):LongArray
}