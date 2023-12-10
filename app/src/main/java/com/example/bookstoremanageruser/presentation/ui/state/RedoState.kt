package com.example.bookstoremanageruser.presentation.ui.state

import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity

data class RedoState(
    val isLoading: Boolean = false,
    val bookReservationState: BookReservedEntity = BookReservedEntity()
)
