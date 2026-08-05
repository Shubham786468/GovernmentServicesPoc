package com.example.governmentservicepoc.domain.usecase.passport

class CheckPassportStatusUseCase {

    suspend operator fun invoke(
        appId: String
    ): String {

        return "UNDER_REVIEW"
    }
}