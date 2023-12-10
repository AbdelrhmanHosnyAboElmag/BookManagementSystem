package com.example.bookstoremanageruser.presentation.ui.compound

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.bookstoremanageruser.data.local.entity.BookType

// Function to get color based on book type
@Composable
fun getBookTypeColor(bookType: BookType): Color {
    return when (bookType) {
        BookType.HORROR -> Color.Red
        BookType.ADVENTURE -> Color.Blue
        BookType.FICTION -> Color.Green
        BookType.ACTION -> Color.Magenta
        BookType.SCIENTIFIC -> Color.Gray
    }
}
