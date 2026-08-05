package com.example.governmentservicepoc.domain.usecase

class CheckStatusUseCase {

    suspend operator fun invoke(
        appId: String
    ): String {

        return "UNDER_REVIEW"
    }
}