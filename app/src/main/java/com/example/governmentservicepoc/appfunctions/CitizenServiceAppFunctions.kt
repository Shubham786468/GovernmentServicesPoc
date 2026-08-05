package com.example.governmentservicepoc.appfunctions

import android.util.Log
import androidx.appfunctions.AppFunctionContext
import androidx.appfunctions.service.AppFunction
import com.example.governmentservicepoc.domain.model.ApplicationResult
import com.example.governmentservicepoc.domain.model.StatusResult
import com.example.governmentservicepoc.domain.model.ApplicationRequest
import com.example.governmentservicepoc.domain.usecase.CheckStatusUseCase
import com.example.governmentservicepoc.domain.usecase.EligibilityUseCase
import com.example.governmentservicepoc.domain.usecase.SubmitApplicationUseCase
import javax.inject.Inject

/**
 * This class defines the app functions for the Citizen Service application.
 * It provides methods to submit income certificate applications, check application status,
 * generate certificates, and verify income eligibility.
 *
 * @property submitUseCase The use case for submitting applications.
 * @property eligibilityUseCase The use case for checking income eligibility.
 * @property checkStatusUseCase The use case for checking application status.
 */
class CitizenServiceAppFunctions @Inject constructor(
    private val submitUseCase: SubmitApplicationUseCase,
    private val eligibilityUseCase: EligibilityUseCase,
    private val checkStatusUseCase: CheckStatusUseCase
) {

    /**
     * This function submits an income certificate application base
     * on the provided details.
     *
     * @param appFunctionContext The context of the app function.
     * @param fullName The full name of the applicant.
     * @param aadhaar The Aadhaar number of the applicant.
     * @param annualIncome The annual income of the applicant.
     * @param panCard The PAN card number of the applicant.
     * @param gender The gender of the applicant.
     * @return An [ApplicationResult] object containing the application ID and its submission status.
     */
    @AppFunction(isDescribedByKDoc = true)
    suspend fun submitIncomeCertificate(
        appFunctionContext: AppFunctionContext,
        fullName: String,
        aadhaar: String,
        annualIncome: Double,
        panCard: String,
        gender: String
    ): ApplicationResult {

        val applicationId =
            submitUseCase(
                ApplicationRequest(
                    fullName = fullName,
                    aadhaar = aadhaar,
                    annualIncome = annualIncome,
                    panCard = panCard,
                    gender = gender
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

    /**
     * This function checks the status of an application based on
     * the provided application ID.
     *
     * @param appFunctionContext The context of the app function.
     * @param applicationId The unique identifier of the application whose status is to be checked.
     * @return A [StatusResult] object containing the application ID and its current status.
     */
    @AppFunction(isDescribedByKDoc = true)
    suspend fun checkApplicationStatus(
        appFunctionContext: AppFunctionContext,
        applicationId: String
    ): StatusResult {

        Log.d(
            "AppFunction",
            "checkApplicationStatus invoked"
        )

        return StatusResult(
            applicationId = applicationId,
            status = checkStatusUseCase(
                applicationId
            )
        )
    }

    /**
     * This function generates a certificate for a given application ID.
     *
     * @param appFunctionContext The context of the app function.
     * @param applicationId The unique identifier of the application for which the certificate is to be generated.
     * @return A string message indicating that the certificate has been generated for the specified application ID.
     */
    @AppFunction(isDescribedByKDoc = true)
    suspend fun generateCertificate(
        appFunctionContext: AppFunctionContext,
        applicationId: String
    ): String {
        Log.d(
            "AppFunction",
            "generateCertificate invoked"
        )
        return "Certificate Generated For $applicationId"
    }

    /**
     * This function verifies the income eligibility based on the provided annual income.
     *
     * @param appFunctionContext The context of the app function.
     * @param annualIncome The annual income to be checked for eligibility.
     * @return A boolean value indicating whether the provided annual income meets the eligibility criteria.
     */
    @AppFunction(isDescribedByKDoc = true)
    suspend fun verifyIncomeEligibility(
        appFunctionContext: AppFunctionContext,
        annualIncome: Double
    ): Boolean {

        Log.d(
            "AppFunction",
            "verifyIncomeEligibility invoked"
        )

        return eligibilityUseCase(
            annualIncome
        )
    }

}