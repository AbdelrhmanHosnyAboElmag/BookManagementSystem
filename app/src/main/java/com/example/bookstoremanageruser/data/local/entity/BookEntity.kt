package com.example.bookstoremanageruser.data.local.entity

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookstoremanageruser.R
import com.example.bookstoremanageruser.desginpattern.decorator.ComponentBook
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String = "test",
    @DrawableRes val image: Int = R.drawable.ic_launcher_background,
    val bookType: BookType = BookType.ACTION,
    val rate: Float = 2f,
    val author: String = "atfdsd",
    val publishDate: String = "2013/3/3",
    val reservation: Reservation = Reservation.AVAILABLE,
) : java.io.Serializable

@Entity
data class BookReservedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String = "test",
    @DrawableRes val image: Int = R.drawable.ic_launcher_background,
    val bookType: BookType = BookType.ACTION,
    val rate: Float = 2f,
    val author: String = "atfdsd",
    val reservationDate: Reservation = Reservation.AVAILABLE,
    val optionalNotes:String = ""
): com.example.bookstoremanageruser.desginpattern.decorator.ComponentBook {
    override fun optionalReservedNoteEntity(): BookReservedEntity {
        return this
    }
}

enum class BookType {
    ACTION, ADVENTURE, HORROR, SCIENTIFIC, FICTION
}

enum class Reservation {
    AVAILABLE, RESERVED, CHECK_OUT
}

enum class SortBy {
    TITLE,
    PUBLISH_DATE,
    AUTHOR
}