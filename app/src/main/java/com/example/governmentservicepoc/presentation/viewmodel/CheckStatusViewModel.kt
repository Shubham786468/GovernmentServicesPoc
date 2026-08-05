package com.example.governmentservicepoc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.governmentservicepoc.appfunctions.AppFunctionRegistry
import com.example.governmentservicepoc.domain.usecase.CheckStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonPrimitive
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class CheckStatusViewModel @Inject constructor(
    private val checkStatusUseCase: CheckStatusUseCase,
    private val appFunctionRegistry: AppFunctionRegistry
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _status = MutableStateFlow<String?>(null)
    val status = _status.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    // Replace with actual logic to retrieve the application ID
    private val applicationId: MutableStateFlow<String?> = MutableStateFlow(null)

    fun checkStatus(appId: String) {
        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null
            _status.value = null

            applicationId.value = appId
            delay(2000.milliseconds)

            runCatching {
//                appFunctionRegistry.execute()
                checkStatusUseCase(appId)
            }
                .onSuccess {
                    _status.value = it
                }
                .onFailure {
                    _error.value = it.message
                }

            _isLoading.value = false
        }
    }

    fun getApplicationId(): String {
        return (applicationId.value
            ?: "APP-2026").uppercase() // Replace with actual logic to retrieve the application ID
    }
}