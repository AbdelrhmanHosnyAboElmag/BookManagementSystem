package com.example.bookstoremanageruser.presentation.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.BookType
import com.example.bookstoremanageruser.data.local.entity.Reservation
import com.example.bookstoremanageruser.desginpattern.state.AvailableState
import com.example.bookstoremanageruser.desginpattern.state.CheckOutState
import com.example.bookstoremanageruser.desginpattern.state.ReservationContext
import com.example.bookstoremanageruser.desginpattern.state.ReservedState
import com.example.bookstoremanageruser.presentation.ui.compound.DatePicker
import com.example.bookstoremanageruser.presentation.ui.compound.RadioButtonBook
import com.example.bookstoremanageruser.presentation.viewmodel.AdminViewModel

@Composable
fun AdminScreen(adminViewModel: AdminViewModel = hiltViewModel()) {
    FormSubmit(adminViewModel)
    val submitResultState = adminViewModel.addBookStateFlow.collectAsState().value
    when {
        submitResultState.isAddSuccess -> {
            Toast.makeText(LocalContext.current, "add success", Toast.LENGTH_SHORT).show()
        }

        submitResultState.isLoading -> {

        }

        submitResultState.isError.isNotEmpty() -> {
            Toast.makeText(LocalContext.current, "something wrong happened :<", Toast.LENGTH_SHORT)
                .show()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSubmit(adminViewModel: AdminViewModel) {
    var title by remember { mutableStateOf(TextFieldValue()) }
    var author by remember { mutableStateOf(TextFieldValue()) }
    var publishDate by remember { mutableStateOf("") }
    var bookType by remember { mutableStateOf(BookType.SCIENTIFIC) }
    var reservationType by remember { mutableStateOf(Reservation.RESERVED) }

    val isButtonEnabled by remember {
        derivedStateOf {
            title.text.isNotEmpty() && author.text.isNotEmpty() && publishDate.isNotEmpty()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )

        DatePicker(onDateChoose = { publishDate = it })

        val bookTypeOptions = BookType.values().toList()
        RadioButtonBook(
            list = bookTypeOptions,
            selectedItem = bookType,
            onItemClick = { selectedBookType ->
                bookType = selectedBookType
                Log.d("test-subscriber", "Selected BookType: $selectedBookType")
            }
        )

        val reservationOptions =
            listOf(Reservation.RESERVED, Reservation.CHECK_OUT, Reservation.RESERVED)
        RadioButtonBook(
            list = reservationOptions,
            selectedItem = reservationType,
            onItemClick = { selectedReservationType ->
                reservationType = selectedReservationType
            }
        )

        Button(
            onClick = {
                adminViewModel.notifyGenreSubscriber(bookType)
                adminViewModel.addBooksToDatabase(
                    BookEntity(
                        title = title.text,
                        author = author.text,
                        publishDate = publishDate,
                        bookType = bookType,
                        reservation = stateChoose(reservationType)
                    )
                )
            },
            enabled = isButtonEnabled
        ) {
            Text("Submit")
        }
    }
}

fun stateChoose(reservation: Reservation): Reservation {
    val reservationContext = ReservationContext()
    return when (reservation) {
        Reservation.AVAILABLE -> {
            reservationContext.changeState(AvailableState())
            reservationContext.returnState()
        }

        Reservation.CHECK_OUT -> {
            reservationContext.changeState(CheckOutState())
            reservationContext.returnState()
        }

        Reservation.RESERVED -> {
            reservationContext.changeState(ReservedState())
            reservationContext.returnState()
        }
    }
}
