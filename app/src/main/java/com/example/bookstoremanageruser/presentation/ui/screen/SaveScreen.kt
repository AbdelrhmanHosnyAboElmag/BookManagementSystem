package com.example.bookstoremanageruser.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity
import com.example.bookstoremanageruser.presentation.ui.compound.ItemBookReserved
import com.example.bookstoremanageruser.presentation.viewmodel.SaveViewModel

@Composable
fun SaveScreen(
    saveViewModel: SaveViewModel = hiltViewModel(),
    onNavigateToDetailScreen: (BookReservedEntity) -> Unit
) {
    ListBookReserved(
        listBookItemEntity = saveViewModel.bookStateFlow.collectAsState().value.bookReservedEntityLists,
        itemDelete = { saveViewModel.removeReserved(it) }) {
        onNavigateToDetailScreen(it)
    }
    val submitResultState = saveViewModel.removeBookReservedStateFlow.collectAsState().value
    when {
        submitResultState.removeBookReserved > 0 -> {
            Toast.makeText(LocalContext.current, "remove success", Toast.LENGTH_SHORT).show()
        }

        submitResultState.isLoading -> {

        }

        submitResultState.isError.isNotEmpty() -> {
            Toast.makeText(LocalContext.current, "something wrong happened :<", Toast.LENGTH_SHORT)
                .show()
        }
    }
}

@Composable
fun ListBookReserved(
    listBookItemEntity: List<BookReservedEntity>, itemDelete: (Long) -> Unit,
    onNavigateToDetailScreen: (BookReservedEntity) -> Unit
) {
    LazyColumn {
        items(listBookItemEntity) { book ->
            ItemBookReserved(bookEntityItem = book,
                itemDelete = { itemDelete(it) }) {
                onNavigateToDetailScreen(it)
            }
        }
    }
}

