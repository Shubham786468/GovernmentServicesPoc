package com.example.governmentservicepoc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.governmentservicepoc.domain.usecase.EligibilityUseCase
import com.example.governmentservicepoc.domain.usecase.passport.VerifyDocumentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class DocumentEligibilityViewModel @Inject constructor(
    private val eligibilityUseCase: VerifyDocumentsUseCase
) : ViewModel() {

    private val _snackbarMessage = MutableSharedFlow<String>()
    val snackbarMessage = _snackbarMessage.asSharedFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isEligible = MutableStateFlow<Boolean?>(null)
    val isEligible = _isEligible.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun checkEligibility(aadhaarNumber: String) {
        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null
            _isEligible.value = null

            delay(2000.milliseconds)

            runCatching {
                eligibilityUseCase(aadhaarNumber)
            }
                .onSuccess {
                    _isEligible.value = it
                }
                .onFailure {
                    _error.value = it.message
                }

            _isLoading.value = false
        }
    }

    fun updateSnackbarEvent(string: String) {
        viewModelScope.launch {
            _error.value = string
            _snackbarMessage.emit(string)
        }
    }
}
