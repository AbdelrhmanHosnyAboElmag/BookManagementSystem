package com.example.bookstoremanageruser.desginpattern.state

import com.example.bookstoremanageruser.data.local.entity.Reservation

class ReservationContext {
    private var state: ReservationState

    init {
        // Initial state is available
        state = AvailableState()
    }

    fun changeState(newState: ReservationState) {
        state = newState
    }

    fun returnState():Reservation{
       return state.returnState()
    }
}
