package com.vis.flowtestapplication.ui.errorhandling.emitall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.vis.flowtestapplication.data.api.ApiHelper
import com.vis.flowtestapplication.data.local.DatabaseHelper
import com.vis.flowtestapplication.data.model.ApiUser
import com.vis.flowtestapplication.ui.base.UiState
import com.vis.flowtestapplication.utils.DispatcherProvider

class EmitAllViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper,
    val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ApiUser>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<ApiUser>>> = _uiState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(dispatcherProvider.main) {
            _uiState.value = UiState.Loading
            apiHelper.getUsers().catch {
                emitAll(flowOf(emptyList()))
            }.zip(apiHelper.getUsersWithError().catch {
                emitAll(flowOf(emptyList()))
            }) { usersFromApi, moreUsersFromApi ->
                val allUsersFromApi = mutableListOf<ApiUser>()
                allUsersFromApi.addAll(usersFromApi)
                allUsersFromApi.addAll(moreUsersFromApi)
                return@zip allUsersFromApi
            }.flowOn(dispatcherProvider.io).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

}