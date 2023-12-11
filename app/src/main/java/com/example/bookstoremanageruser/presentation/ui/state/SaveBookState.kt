package com.example.bookstoremanageruser.presentation.ui.state

data class SaveBookState(
    val isSaveDatabase: Boolean = false,
    val isRemovedDatabase: Boolean = false,
    val message:String = ""
)