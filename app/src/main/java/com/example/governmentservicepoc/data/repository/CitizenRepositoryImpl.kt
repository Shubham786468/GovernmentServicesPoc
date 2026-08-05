package com.example.governmentservicepoc.data.repository

import com.example.governmentservicepoc.data.api.GovernmentApi
import com.example.governmentservicepoc.data.remote.ApplicationIncomeRequestDto
import com.example.governmentservicepoc.data.remote.PassportApplicationRequestDto
import com.example.governmentservicepoc.domain.model.ApplicationRequest
import com.example.governmentservicepoc.domain.model.PassportApplicationRequest
import com.example.governmentservicepoc.domain.repository.CitizenRepository

class CitizenRepositoryImpl(
    private val api: GovernmentApi
) : CitizenRepository {

    override suspend fun submitApplication(
        request: ApplicationRequest
    ): String {

        val response =
            api.submitApplication(

                ApplicationIncomeRequestDto(

                    fullName =
                        request.fullName,

                    aadhaar =
                        request.aadhaar,

                    annualIncome =
                        request.annualIncome,

                    panCard = request.panCard,

                    gender =
                        request.gender
                )
            )

        return response.applicationId
    }

    override suspend fun verifyIncome(
        income: Double
    ): Boolean {

        return api.verifyIncome(
            mapOf(
                "income" to income
            )
        ).verified
    }

    override suspend fun submitPassportApplication(request: PassportApplicationRequest): String {
        val response = api.submitPassportApplication(
            PassportApplicationRequestDto(
                fullName = request.fullName,
                dateOfBirth = request.dateOfBirth,
                gender = request.gender,
                address = request.address,
                nationality = request.nationality,
                aadhaarNumber = request.aadhaar,
                mobileNumber = request.mobileNumber,
                email = request.email
            )
        )

        return response.applicationId
    }

    override suspend fun verifyDocuments(aadhaar: String): Boolean {

        return api.verifyPassportDocuments(
            mapOf(
                "aadhaar" to aadhaar
            )
        ).verified
    }

    override suspend fun schedulePoliceVerification(mobileNumber: String): Boolean {
        return api.schedulePoliceVerification(
            mapOf(
                "mobileNumber" to mobileNumber
            )
        ).scheduled
    }


}