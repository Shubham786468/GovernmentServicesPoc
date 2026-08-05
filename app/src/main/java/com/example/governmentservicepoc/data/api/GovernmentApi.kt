package com.example.governmentservicepoc.data.api

import com.example.governmentservicepoc.data.remote.ApplicationIncomeRequestDto
import com.example.governmentservicepoc.data.remote.ApplicationResponseDto
import com.example.governmentservicepoc.data.remote.PassportApplicationRequestDto
import com.example.governmentservicepoc.data.remote.PassportPoliceVerificationResponseDto
import com.example.governmentservicepoc.data.remote.VerifyIncomeResponseDto
import com.example.governmentservicepoc.data.remote.VerifyPassportDocumentsResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface GovernmentApi {

    @POST("applications/submit")
    suspend fun submitApplication(
        @Body request: ApplicationIncomeRequestDto
    ): ApplicationResponseDto

    @POST("verify-income")
    suspend fun verifyIncome(
        @Body request: Map<String, Double>
    ): VerifyIncomeResponseDto

    @POST("applications/submit-passport")
    suspend fun submitPassportApplication(
        @Body request: PassportApplicationRequestDto
    ): ApplicationResponseDto

    @POST("verify-passport-documents")
    suspend fun verifyPassportDocuments(
        @Body request: Map<String, String>
    ): VerifyPassportDocumentsResponseDto


    @POST("schedule-police-verification")
    suspend fun schedulePoliceVerification(
        @Body request: Map<String, String>
    ): PassportPoliceVerificationResponseDto
}