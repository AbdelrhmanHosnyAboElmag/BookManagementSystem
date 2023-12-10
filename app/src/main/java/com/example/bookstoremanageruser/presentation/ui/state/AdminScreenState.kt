package com.example.bookstoremanageruser.presentation.ui.state

data class AdminScreenState(
    val isLoading: Boolean = false,
    val isAddSuccess: Boolean = false,
    val isError: String = ""
)