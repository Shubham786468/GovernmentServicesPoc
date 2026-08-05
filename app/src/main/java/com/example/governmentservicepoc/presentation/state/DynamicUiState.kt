package com.example.governmentservicepoc.presentation.state

import com.example.governmentservicepoc.domain.model.ScreenMetadata

sealed interface DynamicUiState {

    data object Loading :
        DynamicUiState

    data class Success(
        val metadata: ScreenMetadata
    ) : DynamicUiState

    data class Error(
        val message: String
    ) : DynamicUiState
}