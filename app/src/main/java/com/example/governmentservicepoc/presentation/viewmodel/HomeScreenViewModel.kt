package com.example.governmentservicepoc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.governmentservicepoc.domain.usecase.HomeServiceSelectionUseCase
import com.example.governmentservicepoc.presentation.state.SubmissionState
import com.example.governmentservicepoc.presentation.ui.a2ui.model.A2UiSchema
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeServiceSelectionUseCase: HomeServiceSelectionUseCase
) : ViewModel() {
    private val _serviceName = MutableSharedFlow<String>()
    val serviceName: SharedFlow<String> = _serviceName.asSharedFlow()

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error.asStateFlow()


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun startLoading() {
        _isLoading.value = true
    }

    fun stopLoading() {
        _isLoading.value = false
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        viewModelScope.launch {
            _error.value = throwable.message ?: "Server Issue"
            stopLoading()
        }
    }

    fun getServiceName(name: String) {
        startLoading()
        viewModelScope.launch(coroutineExceptionHandler) {
            val result = withContext(Dispatchers.IO) {
                homeServiceSelectionUseCase(name)
            }
            _serviceName.emit(result)
            stopLoading()
        }
    }

}
