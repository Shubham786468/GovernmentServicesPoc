package com.example.governmentservicepoc.domain.agents.incomecertificate

import com.example.governmentservicepoc.domain.agents.WorkflowAgent
import com.example.governmentservicepoc.domain.model.AgentResult
import com.example.governmentservicepoc.domain.model.WorkflowContext
import com.example.governmentservicepoc.utils.AppConstants

class EligibilityAgent : WorkflowAgent {

    override suspend fun execute(
        context: WorkflowContext
    ): AgentResult {

        val income =
            context.formData[AppConstants.ANNUAL_INCOME]?.toDoubleOrNull() ?: 0.0

        return if (income <= AppConstants.MAX_ANNUAL_INCOME) {
            AgentResult(true)
        } else {
            AgentResult(
                false,
                "Income limit exceeds."
            )
        }
    }
}


//class EligibilityAgent {
//
//    fun evaluate(
//        income: Double
//    ): Boolean {
//
//        return income <= 500000
//    }
//}

