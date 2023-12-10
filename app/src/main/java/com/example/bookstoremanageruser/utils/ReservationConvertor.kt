package com.example.bookstoremanageruser.utils

import androidx.room.TypeConverter
import com.example.bookstoremanageruser.data.local.entity.Reservation
import com.google.gson.Gson

class ReservationConvertor {
    private val gson = Gson()

    @TypeConverter
    fun fromBook(bookReservation: Reservation?): String? {
        return gson.toJson(bookReservation)
    }

    @TypeConverter
    fun toBook(bookReservation: String?): Reservation? {
        return gson.fromJson(bookReservation, Reservation::class.java)
    }
}