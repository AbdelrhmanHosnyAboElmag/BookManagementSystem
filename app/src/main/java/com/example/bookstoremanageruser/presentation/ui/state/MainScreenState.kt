package com.example.bookstoremanageruser.presentation.ui.state

import com.example.bookstoremanageruser.data.local.entity.BookEntity

data class MainScreenState(
    val isLoading: Boolean = false,
    val bookEntityLists: List<BookEntity> = listOf(BookEntity()),
    val isError: String = ""
)