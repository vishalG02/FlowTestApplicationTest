package com.vis.flowtestapplication.ui.retryexponentialbackoff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.vis.flowtestapplication.data.api.ApiHelper
import com.vis.flowtestapplication.data.local.DatabaseHelper
import com.vis.flowtestapplication.utils.DispatcherProvider
import com.vis.flowtestapplication.ui.base.UiState
import java.io.IOException

class RetryExponentialBackoffModel(
    val apiHelper: ApiHelper,
    dbHelper: DatabaseHelper,
    val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<String>>(UiState.Loading)

    val uiState: StateFlow<UiState<String>> = _uiState

    fun startTask() {
        viewModelScope.launch(dispatcherProvider.main) {
            _uiState.value = UiState.Loading
            // do a long running task
            var currentDelay = 1000L
            val delayFactor = 2
            doLongRunningTask()
                .flowOn(dispatcherProvider.default)
                .retry(retries = 3) { cause ->
                    if (cause is IOException) {
                        delay(currentDelay)
                        currentDelay = (currentDelay * delayFactor)
                        return@retry true
                    } else {
                        return@retry false
                    }
                }
                .catch {
                    _uiState.value = UiState.Error("Something Went Wrong")
                }
                .collect {
                    _uiState.value = UiState.Success("Task Completed")
                }
        }
    }

    private fun doLongRunningTask(): Flow<Int> {
        return flow {
            // your code for doing a long running task
            // Added delay, random number, and exception to simulate

            delay(2000)

            val randomNumber = (0..2).random()

            if (randomNumber == 0) {
                throw IOException()
            } else if (randomNumber == 1) {
                throw IndexOutOfBoundsException()
            }

            delay(2000)
            emit(0)
        }
    }

}