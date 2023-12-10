package com.example.bookstoremanageruser.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstoremanageruser.domain.usecase.GetReservedBookEntity
import com.example.bookstoremanageruser.domain.usecase.RemoveBookUseCase
import com.example.bookstoremanageruser.presentation.ui.state.MainScreenState
import com.example.bookstoremanageruser.presentation.ui.state.RemoveScreenState
import com.example.bookstoremanageruser.presentation.ui.state.SaveScreenState
import com.example.bookstoremanageruser.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    private val getReservedBook: GetReservedBookEntity,
    private val removeReservedBook: RemoveBookUseCase
) :
    ViewModel() {
    private val _bookReservedStateFlow = MutableStateFlow(SaveScreenState())
    val bookStateFlow: StateFlow<SaveScreenState> = _bookReservedStateFlow.asStateFlow()

    private val _removeBookReservedStateFlow =
        MutableStateFlow(RemoveScreenState())
    val removeBookReservedStateFlow: StateFlow<RemoveScreenState> =
        _removeBookReservedStateFlow.asStateFlow()

    init {
        getReservedBookEntity()
    }


    private fun getReservedBookEntity() {
        getReservedBook().onEach {
            when (it) {
                is DataResult.Success -> {
                    _bookReservedStateFlow.value =
                        SaveScreenState(bookReservedEntityLists = it.data ?: listOf())
                }

                is DataResult.Error -> {
                    _bookReservedStateFlow.value = SaveScreenState(isError = it.message.toString())
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun removeReserved(id: Long) {
        removeReservedBook(id).onEach {
            when (it) {
                is DataResult.Success -> {
                    _removeBookReservedStateFlow.value =
                        RemoveScreenState(removeBookReserved = it.data ?: 0)
                    getReservedBookEntity()
                }

                is DataResult.Error -> {
                    _removeBookReservedStateFlow.value =
                        RemoveScreenState(isError = it.message.toString())
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }
}