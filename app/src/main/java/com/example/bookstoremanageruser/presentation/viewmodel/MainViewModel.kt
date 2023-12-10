package com.example.bookstoremanageruser.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.BookType
import com.example.bookstoremanageruser.desginpattern.observer.ObserverClient
import com.example.bookstoremanageruser.domain.usecase.GetBookListUseCase
import com.example.bookstoremanageruser.presentation.ui.state.MainScreenState
import com.example.bookstoremanageruser.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBook: GetBookListUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _bookStateFlow = MutableStateFlow(MainScreenState())
    val bookStateFlow: StateFlow<MainScreenState> = _bookStateFlow.asStateFlow()
    init {
        getBookList()
    }

    private fun getBookList() {
        getBook().onEach {
            when (it) {
                is DataResult.Success -> {
                    _bookStateFlow.value = MainScreenState(bookEntityLists = it.data ?: listOf())
                }

                is DataResult.Error -> {
                    _bookStateFlow.value = MainScreenState(isError = it.message.toString())
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun updateWithSort(listBookEntity: List<BookEntity>) {
        _bookStateFlow.value = MainScreenState(bookEntityLists = listBookEntity)
    }

    fun addGenreSubscriber(bookType: BookType) {
        ObserverClient.addGenreSubscriber(bookType, context = context)
    }
}