package com.example.governmentservicepoc.appfunctions

import android.util.Log
import androidx.appfunctions.AppFunctionContext
import androidx.appfunctions.service.AppFunction
import com.example.governmentservicepoc.domain.model.ApplicationResult
import com.example.governmentservicepoc.domain.model.PassportApplicationRequest
import com.example.governmentservicepoc.domain.model.StatusResult
import com.example.governmentservicepoc.domain.usecase.passport.CheckPassportStatusUseCase
import com.example.governmentservicepoc.domain.usecase.passport.SchedulePoliceVerificationUseCase
import com.example.governmentservicepoc.domain.usecase.passport.SubmitPassportUseCase
import com.example.governmentservicepoc.domain.usecase.passport.VerifyDocumentsUseCase
import javax.inject.Inject

class PassportServiceAppFunctions @Inject constructor(
    private val submitPassportUseCase: SubmitPassportUseCase,
    private val verifyDocumentsUseCase: VerifyDocumentsUseCase,
    private val checkPassportStatusUseCase: CheckPassportStatusUseCase,
    private val policeVerificationUseCase: SchedulePoliceVerificationUseCase
) {

    @AppFunction(isDescribedByKDoc = true)
    suspend fun verifyDocuments(
        appFunctionContext: AppFunctionContext,
        aadhaarNumber: String
    ): Boolean {
        Log.d(
            "AppFunction",
            "verifyDocuments invoked"
        )
        return verifyDocumentsUseCase(
            aadhaarNumber
        )
    }

    @AppFunction(isDescribedByKDoc = true)
    suspend fun submitPassportApplication(
        appFunctionContext: AppFunctionContext,
        fullName: String,
        dateOfBirth: String,
        gender: String,
        address: String,
        nationality: String,
        aadhaarNumber: String,
        mobileNumber: String,
        email: String
    ): ApplicationResult {
        val applicationId =
            submitPassportUseCase(
                PassportApplicationRequest(
                    fullName = fullName,
                    dateOfBirth = dateOfBirth,
                    gender = gender,
                    address = address,
                    nationality = nationality,
                    aadhaar = aadhaarNumber,
                    mobileNumber = mobileNumber,
                    email = email
                )
            )

        Log.d(
            "AppFunction",
            "submitIncomeCertificate invoked"
        )

        return ApplicationResult(
            applicationId = applicationId,
            status = "SUBMITTED"
        )
    }

    @AppFunction(isDescribedByKDoc = true)
    suspend fun checkPassportApplicationStatus(
        appFunctionContext: AppFunctionContext,
        applicationId: String
    ): StatusResult {
        Log.d(
            "AppFunction",
            "checkPassportApplicationStatus invoked"
        )

        return StatusResult(
            applicationId = applicationId,
            status = checkPassportStatusUseCase(
                applicationId
            )
        )
    }

    @AppFunction(isDescribedByKDoc = true)
    suspend fun schedulePoliceVerification(
        appFunctionContext: AppFunctionContext,
        applicationId: String
    ): Boolean {
        return policeVerificationUseCase(
            applicationId
        )
    }

    @AppFunction(isDescribedByKDoc = true)
    suspend fun generateAcknowledgement(
        appFunctionContext: AppFunctionContext,
        applicationId: String
    ): String {
        Log.d(
            "AppFunction",
            "generatePassportAcknowledgement invoked"
        )
        return "Acknowledgement Generated For $applicationId"
    }

}