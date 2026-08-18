package com.example.governmentservicepoc.appfunctions

import com.example.governmentservicepoc.domain.model.ApplicationRequest
import com.example.governmentservicepoc.domain.model.PassportApplicationRequest
import com.example.governmentservicepoc.domain.usecase.CheckStatusUseCase
import com.example.governmentservicepoc.domain.usecase.EligibilityUseCase
import com.example.governmentservicepoc.domain.usecase.SubmitApplicationUseCase
import com.example.governmentservicepoc.domain.usecase.passport.CheckPassportStatusUseCase
import com.example.governmentservicepoc.domain.usecase.passport.SchedulePoliceVerificationUseCase
import com.example.governmentservicepoc.domain.usecase.passport.SubmitPassportUseCase
import com.example.governmentservicepoc.domain.usecase.passport.VerifyDocumentsUseCase
import com.example.governmentservicepoc.utils.AppConstants
import com.example.governmentservicepoc.utils.addFormStart
import javax.inject.Inject
import javax.inject.Singleton

/**
 * AppFunctionRegistry is responsible for executing dynamic functions based
 * on the provided function name and payload.
 * It uses various use cases to perform specific operations related to government services.
 *
 * @property submitUseCase Use case for submitting an application.
 * @property eligibilityUseCase Use case for checking income eligibility.
 * @property checkStatusUseCase Use case for checking the status of an application.
 */
@Singleton
class AppFunctionRegistry @Inject constructor(
    private val submitUseCase: SubmitApplicationUseCase,
    private val eligibilityUseCase: EligibilityUseCase,
    private val checkStatusUseCase: CheckStatusUseCase,
    private val checkPassportStatusUseCase: CheckPassportStatusUseCase,
    private val verifyDocumentsUseCase: VerifyDocumentsUseCase,
    private val schedulePoliceVerificationUseCase: SchedulePoliceVerificationUseCase,
    private val submitPassportUseCase: SubmitPassportUseCase
) : DynamicFunctionExecutor {

    override suspend fun execute(
        functionName: String,
        payload: Map<String, Any>
    ): Any {

        return when (functionName) {

            /**
             * Income Certificate Service Functions
             */

            "verifyIncomeEligibility" -> {
                eligibilityUseCase(
                    payload[AppConstants.ANNUAL_INCOME]
                        .toString()
                        .toDouble()
                )
            }

            "checkApplicationStatus" -> {
                checkStatusUseCase(
                    payload[AppConstants.APPLICATION_ID]
                        .toString()
                )
            }

            "generateCertificate" -> {
                val applicationId =
                    payload[AppConstants.APPLICATION_ID]
                        .toString()

                "Certificate Generated For $applicationId"
            }

            "submitIncomeCertificate" -> {
                submitUseCase(
                    ApplicationRequest(
                        fullName = payload[AppConstants.FULL_NAME.addFormStart()].toString(),

                        aadhaar = payload[AppConstants.AADHAAR_NUMBER.addFormStart()].toString(),

                        annualIncome = payload[AppConstants.ANNUAL_INCOME.addFormStart()].toString().toDouble(),

                        gender = payload[AppConstants.GENDER.addFormStart()].toString()
                    )
                )
            }

            /**
             * Passport Service Functions
             */

            "verifyPassportDocuments" -> {
                verifyDocumentsUseCase(
                    payload[AppConstants.AADHAAR_NUMBER]
                        .toString()
                )
            }

            "submitPassportApplication" -> {
                submitPassportUseCase(
                    PassportApplicationRequest(
                        fullName = payload[AppConstants.FULL_NAME.addFormStart()].toString(),
                        dateOfBirth = payload[AppConstants.DATE_OF_BIRTH.addFormStart()].toString(),
                        gender = payload[AppConstants.GENDER.addFormStart()].toString(),
                        address = payload[AppConstants.ADDRESS.addFormStart()].toString(),
                        nationality = payload[AppConstants.NATIONALITY.addFormStart()].toString(),
                        aadhaar = payload[AppConstants.AADHAAR_NUMBER.addFormStart()].toString(),
                        mobileNumber = payload[AppConstants.MOBILE_NUMBER.addFormStart()].toString(),
                        email = payload[AppConstants.EMAIL.addFormStart()].toString()
                    )
                )
            }

            "checkPassportApplicationStatus" -> {
                checkPassportStatusUseCase(
                    payload[AppConstants.APPLICATION_ID]
                        .toString()
                )
            }

            "schedulePassportPoliceVerification" -> {
                schedulePoliceVerificationUseCase(
                    payload[AppConstants.MOBILE_NUMBER]
                        .toString()
                )
            }

            "generatePassportAcknowledgement" -> {
                val applicationId = payload[AppConstants.APPLICATION_ID].toString()

                "Passport Acknowledgement Generated For $applicationId"
            }

            else -> {
                throw IllegalArgumentException(
                    "Unknown function: $functionName"
                )
            }
        }
    }
}