package com.example.governmentservicepoc.domain.repository

import com.example.governmentservicepoc.domain.model.ApplicationRequest
import com.example.governmentservicepoc.domain.model.PassportApplicationRequest

interface CitizenRepository {

    suspend fun verifyIncome(
        income: Double
    ): Boolean

    suspend fun submitApplication(
        request: ApplicationRequest
    ): String

    // passport
    suspend fun submitPassportApplication(
        request: PassportApplicationRequest
    ): String

    suspend fun verifyDocuments(
        aadhaar: String
    ): Boolean

    suspend fun schedulePoliceVerification(
        mobileNo: String
    ): Boolean

    //driving license
//    suspend fun submitDrivingLicenseApplication(
//        request: DrivingLicenseApplicationRequest
//    ): String

}