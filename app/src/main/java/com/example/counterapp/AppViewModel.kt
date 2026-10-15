package com.example.counterapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    // Backing property: hanya bisa diubah di dalam ViewModel
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    init {
        reset()
    }

    // Tombol Up: tambah 1
    fun up() {
        _uiState.update { currentState ->
            currentState.copy(counter = currentState.counter + 1)
        }
    }

    // Tombol Down: kurangi 1
    fun down() {
        _uiState.update { currentState ->
            currentState.copy(counter = currentState.counter - 1)
        }
    }

    // Tombol Reset: kembali ke 0
    fun reset() {
        _uiState.value = AppUiState(counter = 0)
    }
}