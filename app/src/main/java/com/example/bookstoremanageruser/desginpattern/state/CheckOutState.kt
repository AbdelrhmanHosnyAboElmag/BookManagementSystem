package com.example.bookstoremanageruser.desginpattern.state

import com.example.bookstoremanageruser.data.local.entity.Reservation

class CheckOutState : ReservationState {
    override fun setState(reservationContext: ReservationContext) {
        reservationContext.changeState(CheckOutState())
    }

    override fun returnState(): Reservation {
        return Reservation.CHECK_OUT
    }


}