package com.example.governmentservicepoc.presentation.state

import com.example.governmentservicepoc.presentation.ui.a2ui.model.A2UiSchema

sealed interface DynamicUiState {

    data object Loading :
        DynamicUiState

    data class Success(
        val a2uiSchema: A2UiSchema
    ) : DynamicUiState

    data class Error(
        val message: String
    ) : DynamicUiState
}