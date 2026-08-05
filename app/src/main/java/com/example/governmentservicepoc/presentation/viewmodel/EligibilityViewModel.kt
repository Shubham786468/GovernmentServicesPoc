package com.example.governmentservicepoc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.governmentservicepoc.domain.usecase.EligibilityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class EligibilityViewModel @Inject constructor(
    private val eligibilityUseCase: EligibilityUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isEligible = MutableStateFlow<Boolean?>(null)
    val isEligible = _isEligible.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun checkEligibility(income: Double) {
        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null
            _isEligible.value = null

            delay(2000.milliseconds)

            runCatching {
                eligibilityUseCase(income)
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
}