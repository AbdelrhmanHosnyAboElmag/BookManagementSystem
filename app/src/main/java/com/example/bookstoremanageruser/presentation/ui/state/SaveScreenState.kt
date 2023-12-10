package com.example.bookstoremanageruser.presentation.ui.state

import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity

data class SaveScreenState(
    val isLoading: Boolean = false,
    val bookReservedEntityLists: List<BookReservedEntity> = listOf(BookReservedEntity()),
    val isError: String = ""
)

data class RemoveScreenState(
    val isLoading: Boolean = false,
    val removeBookReserved: Int = 0,
    val isError: String = ""
)

