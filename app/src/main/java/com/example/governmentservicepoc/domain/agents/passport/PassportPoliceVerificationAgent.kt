package com.example.governmentservicepoc.domain.agents.passport

import com.example.governmentservicepoc.domain.agents.WorkflowAgent
import com.example.governmentservicepoc.domain.model.AgentResult
import com.example.governmentservicepoc.domain.model.WorkflowContext
import com.example.governmentservicepoc.domain.repository.CitizenRepository
import com.example.governmentservicepoc.utils.AppConstants

class PassportPoliceVerificationAgent(
    private val repository: CitizenRepository
) : WorkflowAgent {
    override suspend fun execute(context: WorkflowContext): AgentResult {

        val mobileNo = context.formData[AppConstants.MOBILE_NUMBER] ?: ""

        val policeVerificationStatus = repository.schedulePoliceVerification(mobileNo)

        return if (policeVerificationStatus) {
            AgentResult(
                success = true
            )
        } else {
            AgentResult(
                success = false,
                reason = "Police Verification Failed"
            )
        }
    }
}