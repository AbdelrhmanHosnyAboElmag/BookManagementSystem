package com.example.bookstoremanageruser.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.desginpattern.strategy.ContextSort
import com.example.bookstoremanageruser.desginpattern.strategy.StrategySortAuthor
import com.example.bookstoremanageruser.desginpattern.strategy.StrategySortPublishDate
import com.example.bookstoremanageruser.desginpattern.strategy.StrategySortTitle
import com.example.bookstoremanageruser.presentation.ui.compound.ItemBook
import com.example.bookstoremanageruser.presentation.ui.compound.OptionMenu
import com.example.bookstoremanageruser.presentation.ui.dialog.SortDialog
import com.example.bookstoremanageruser.presentation.ui.dialog.SubscribeDialog
import com.example.bookstoremanageruser.presentation.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    onNavigationToSaveScreen: () -> Unit,
    onNavigateToAdminScreen: () -> Unit,
    onNavigateToDetailScreen: (BookEntity) -> Unit) {
    val showSortDialog = remember { mutableStateOf(false) }
    val showSubscribeDialog = remember { mutableStateOf(false) }
    val contextSort by remember {
        mutableStateOf(ContextSort())
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "BookStore") },
            actions = {
                OptionMenu(clickItem = { optionClick ->
                    if (optionClick.first) {//handle sort
                        showSortDialog.value = true
                    } else if (optionClick.second) {//subscribe for specific type
                        showSubscribeDialog.value = true
                    } else if (optionClick.third) {
                        onNavigationToSaveScreen()
                    }
                },clickAddBook = {
                    onNavigateToAdminScreen()
                })
            })
    }) { it ->
        Column(Modifier.padding(it)) {
            if (showSortDialog.value) {
                SortDialog(setShowDialog = {
                    showSortDialog.value = it
                }) { sortType ->
                    if (sortType.first) {//sort use title
                        contextSort.setStrategy(StrategySortTitle())
                    } else if (sortType.second) {//sort use author
                        contextSort.setStrategy(StrategySortAuthor())
                    } else if (sortType.third) {//sort use date
                        contextSort.setStrategy(StrategySortPublishDate())
                    }
                    mainViewModel.updateWithSort(contextSort.executeStrategy(mainViewModel.bookStateFlow.value.bookEntityLists))
                }
            }
            if (showSubscribeDialog.value) {
                SubscribeDialog(setShowDialog = {
                    showSubscribeDialog.value = it
                }, bookTypeChoice = {
                    mainViewModel.addGenreSubscriber(it)
                })
            }
            ListBook(listBookItemEntity = mainViewModel.bookStateFlow.collectAsState().value.bookEntityLists , onNavigateToDetailScreen = {
                onNavigateToDetailScreen(it)
            })
        }
    }
}

@Composable
fun ListBook(
    listBookItemEntity: List<BookEntity>,
    onNavigateToDetailScreen: (BookEntity) -> Unit,
) {
    LazyColumn {
        items(listBookItemEntity) { book ->
            ItemBook(bookEntityItem = book, onItemClick = {
                onNavigateToDetailScreen(it)
            })
        }
    }
}