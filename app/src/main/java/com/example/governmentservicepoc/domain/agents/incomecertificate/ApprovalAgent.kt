package com.example.governmentservicepoc.domain.agents.incomecertificate

import com.example.governmentservicepoc.domain.agents.WorkflowAgent
import com.example.governmentservicepoc.domain.model.AgentResult
import com.example.governmentservicepoc.domain.model.WorkflowContext

class ApprovalAgent : WorkflowAgent {

    override suspend fun execute(
        context: WorkflowContext
    ): AgentResult {

        return AgentResult(true)
    }
}