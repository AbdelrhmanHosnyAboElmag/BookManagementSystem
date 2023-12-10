package com.example.bookstoremanageruser.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstoremanageruser.data.local.entity.BookEntity
import com.example.bookstoremanageruser.data.local.entity.BookType
import com.example.bookstoremanageruser.desginpattern.observer.ObserverClient
import com.example.bookstoremanageruser.domain.usecase.InsertBookUseCase
import com.example.bookstoremanageruser.presentation.ui.state.AdminScreenState
import com.example.bookstoremanageruser.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(private val insertBookUseCase: InsertBookUseCase) :
    ViewModel() {
    private val _addBookStateFlow = MutableStateFlow(AdminScreenState())
    val addBookStateFlow: StateFlow<AdminScreenState> = _addBookStateFlow.asStateFlow()

    fun addBooksToDatabase(
        bookEntity: BookEntity
    ) {
        insertBookUseCase(com.example.bookstoremanageruser.desginpattern.factory.CreateBookFactory.createBookFactory(bookEntity)).onEach { result ->
            when (result) {
                is DataResult.Error -> {
                    _addBookStateFlow.value = AdminScreenState(isError = result.message.toString())
                }

                is DataResult.Loading -> {

                }

                is DataResult.Success -> {
                    _addBookStateFlow.value = AdminScreenState(isAddSuccess = result.data == true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun notifyGenreSubscriber(bookType: BookType){
       ObserverClient.notifyGenreSubscriber(bookType)
    }
}