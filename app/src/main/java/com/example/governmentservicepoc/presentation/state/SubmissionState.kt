package com.example.governmentservicepoc.presentation.state

sealed interface SubmissionState {

    data object Idle : SubmissionState

    data object Loading : SubmissionState

    data class Success(val applicationId: String) : SubmissionState

    data class Rejected(val reason: String) : SubmissionState
    data class Error(val message: String) : SubmissionState
}