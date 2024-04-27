package com.vis.flowtestapplication.ui.base

sealed class UiState<out T> {

    data class Success<T>(val data: T) : UiState<T>()

    data class Error(val message: String) : UiState<Nothing>()

    object Loading : UiState<Nothing>()

}