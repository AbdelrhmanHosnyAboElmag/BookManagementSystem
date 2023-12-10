package com.example.bookstoremanageruser.presentation.ui.state


data class DetailScreenState(
    val isLoading: Boolean = false,
    val isInsert:Boolean = false,
    val isError: String = ""
)