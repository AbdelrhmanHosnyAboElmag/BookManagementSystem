package com.example.bookstoremanageruser.utils

import androidx.room.TypeConverter
import com.example.bookstoremanageruser.data.local.entity.BookType
import com.google.gson.Gson

class BookTypeConvertor {
    private val gson = Gson()

    @TypeConverter
    fun fromBookType(book: BookType?): String? {
        return gson.toJson(book)
    }

    @TypeConverter
    fun toBookType(bookJson: String?): BookType? {
        return gson.fromJson(bookJson, BookType::class.java)
    }
}