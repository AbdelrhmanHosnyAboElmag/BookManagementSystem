package com.example.bookstoremanageruser.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity
import com.example.bookstoremanageruser.domain.usecase.InsertReservedBookUseCase
import com.example.bookstoremanageruser.domain.usecase.RedoUseCase
import com.example.bookstoremanageruser.domain.usecase.UndoUseCase
import com.example.bookstoremanageruser.presentation.ui.state.SaveBookState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val insertReservedBookUseCase: InsertReservedBookUseCase,
    private val undoUseCase: UndoUseCase,
    private val redoUseCase: RedoUseCase,
) :
    ViewModel() {
    private val _insertReservedBookStateFlow =
        MutableStateFlow(SaveBookState())
    val insertReservedBookStateFlow: StateFlow<SaveBookState> = _insertReservedBookStateFlow
    fun execute(bookReservedEntity: BookReservedEntity) {
        insertReservedBookUseCase(bookReservedEntity).onEach {
            when {
                it.data == true -> {
                    _insertReservedBookStateFlow.value =
                        SaveBookState(isSaveDatabase = it.data, isRemovedDatabase = false)
                }

                it.message?.isNotEmpty() == true -> {
                    _insertReservedBookStateFlow.value =
                        SaveBookState(message = it.message)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun undoSave() {
        undoUseCase().onEach {
            _insertReservedBookStateFlow.value = SaveBookState(isRemovedDatabase = it, isSaveDatabase = false)
        }.launchIn(viewModelScope)
    }

//    fun redoSave(){
//        redoUseCase().onEach {
//            _bookRedoStateFlow.value = RedoState(bookReservationState = it)
//        }.launchIn(viewModelScope)
//    }
}