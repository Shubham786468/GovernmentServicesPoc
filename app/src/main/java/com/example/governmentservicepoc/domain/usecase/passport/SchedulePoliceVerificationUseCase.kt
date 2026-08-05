package com.example.governmentservicepoc.domain.usecase.passport

import com.example.governmentservicepoc.domain.agents.passport.PassportPoliceVerificationAgent
import com.example.governmentservicepoc.domain.model.WorkflowContext

class SchedulePoliceVerificationUseCase(
    private val schedulePoliceVerificationAgent: PassportPoliceVerificationAgent
) {

    suspend operator fun invoke(
        mobileNumber: String
    ): Boolean {

        return schedulePoliceVerificationAgent.execute(
            WorkflowContext(
                mapOf(
                    "mobileNumber" to mobileNumber
                )
            )
        ).success
    }
}