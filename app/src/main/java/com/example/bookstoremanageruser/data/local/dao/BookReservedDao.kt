package com.example.bookstoremanageruser.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity

@Dao
interface BookReservedDao {
    @Query("select * from bookreservedentity")
    fun getReservedBook(): MutableList<BookReservedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg books: BookReservedEntity):LongArray


    @Query("DELETE FROM bookreservedentity WHERE id = :itemId")
    fun deleteById(itemId: Long):Int
}