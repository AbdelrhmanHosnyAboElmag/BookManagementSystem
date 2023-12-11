package com.example.bookstoremanageruser.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity
import com.example.bookstoremanageruser.desginpattern.decorator.BookNoteDecorator
import com.example.bookstoremanageruser.presentation.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    bookEntityItem: BookEntity,
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    val configuration = LocalConfiguration.current
    var optionalNote by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(bookEntityItem.image),
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .height(((configuration.screenHeightDp * 25) / 100).dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = modifier.height(16.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = bookEntityItem.title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = bookEntityItem.author,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Gray
                    )
                )
            }
            Spacer(modifier = modifier.width(16.dp))
            TextField(
                value = optionalNote,
                onValueChange = { optionalNote = it },
                label = { Text("Optional Note") },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                modifier = modifier.weight(1f)
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        saveButton(detailViewModel, bookEntityItem, optionalNote)
    }
}

@Composable
fun saveButton(
    detailViewModel: DetailViewModel, bookEntityItem: BookEntity, optionalNote: String
) {
    val saveBookState = detailViewModel.insertReservedBookStateFlow.collectAsState().value
    val context = LocalContext.current
    var isButtonSave by remember {
        mutableStateOf(true)
    }

    when {
        saveBookState.isSaveDatabase -> {
            isButtonSave = false
            Toast.makeText(context, "add success", Toast.LENGTH_SHORT).show()
        }
        saveBookState.isRemovedDatabase -> {
            isButtonSave = true
            Toast.makeText(context, "undo success", Toast.LENGTH_SHORT).show()
        }
        saveBookState.message.isNotEmpty() -> {}
    }
    Button(
        onClick = {

            if (isButtonSave) {
                detailViewModel.execute(
                    BookNoteDecorator(
                        BookReservedEntity(
                            id = bookEntityItem.id,
                            title = bookEntityItem.title,
                            reservationDate = bookEntityItem.reservation,
                            image = bookEntityItem.image,
                            bookType = bookEntityItem.bookType,
                            rate = bookEntityItem.rate
                        ), optionalNote
                    ).optionalReservedNoteEntity()
                )
            } else {
                detailViewModel.undoSave()
            }
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isButtonSave) {
            Text(text = "save book!!")
        } else {
            Text(text = "undo book!!")
        }
    }
}