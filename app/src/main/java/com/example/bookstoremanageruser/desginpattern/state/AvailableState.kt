package com.example.bookstoremanageruser.desginpattern.state

import com.example.bookstoremanageruser.data.local.entity.Reservation

class AvailableState:ReservationState {
    override fun setState(reservationContext: ReservationContext) {
        reservationContext.changeState(CheckOutState())
    }

    override fun returnState(): Reservation {
        return Reservation.AVAILABLE
    }

}