package com.example.bookstoremanageruser.desginpattern.state

import com.example.bookstoremanageruser.data.local.entity.Reservation


interface ReservationState {
    fun setState(reservationContext: ReservationContext)
    fun returnState():Reservation
}