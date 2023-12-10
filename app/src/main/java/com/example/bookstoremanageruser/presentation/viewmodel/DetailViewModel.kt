package com.example.bookstoremanageruser.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity
import com.example.bookstoremanageruser.domain.usecase.InsertReservedBookUseCase
import com.example.bookstoremanageruser.domain.usecase.RedoUseCase
import com.example.bookstoremanageruser.domain.usecase.UndoUseCase
import com.example.bookstoremanageruser.presentation.ui.state.DetailScreenState
import com.example.bookstoremanageruser.presentation.ui.state.RedoState
import com.example.bookstoremanageruser.presentation.ui.state.UndoState
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
        MutableStateFlow<DetailScreenState>(DetailScreenState())
    val insertReservedBookStateFlow: StateFlow<DetailScreenState> =
        _insertReservedBookStateFlow.asStateFlow()

    private val _bookUndoStateFlow = MutableStateFlow(UndoState())
    val bookUndoStateFlow: StateFlow<UndoState> = _bookUndoStateFlow.asStateFlow()
    private val _bookRedoStateFlow = MutableStateFlow(RedoState())
    val bookRedoStateFlow: StateFlow<RedoState> = _bookRedoStateFlow.asStateFlow()

    fun execute(bookReservedEntity: BookReservedEntity) {
        insertReservedBookUseCase(bookReservedEntity).onEach {
            when {
                it.data == true -> {
                    _insertReservedBookStateFlow.value =
                        DetailScreenState(isInsert = it.data == true)
                }

                it.message?.isNotEmpty() == true -> {
                    _insertReservedBookStateFlow.value =
                        DetailScreenState(isError = it.message)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun undoSave() {
        undoUseCase().onEach {
            _bookUndoStateFlow.value = UndoState(isSuccess = it)
        }.launchIn(viewModelScope)
    }

    fun redoSave(){
        redoUseCase().onEach {
            _bookRedoStateFlow.value = RedoState(bookReservationState = it)
        }.launchIn(viewModelScope)
    }
}