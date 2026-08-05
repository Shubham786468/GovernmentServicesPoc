package com.example.governmentservicepoc.domain.agents

import com.example.governmentservicepoc.domain.agents.incomecertificate.ApprovalAgent
import com.example.governmentservicepoc.domain.agents.incomecertificate.EligibilityAgent
import com.example.governmentservicepoc.domain.agents.incomecertificate.SubmitApplicationAgent
import com.example.governmentservicepoc.domain.agents.incomecertificate.VerificationAgent
import com.example.governmentservicepoc.domain.agents.passport.PassportApprovalAgent
import com.example.governmentservicepoc.domain.agents.passport.PassportDocumentCheckAgent
import com.example.governmentservicepoc.domain.agents.passport.PassportPoliceVerificationAgent
import com.example.governmentservicepoc.domain.agents.passport.SubmitPassportApplicationAgent

/**
 * The AgentRegistry class serves as a directory for all available workflow agents
 * in the system. It provides a centralized way to access different agents based on
 * their names, allowing for easy retrieval and management of agents within the workflow
 * execution process.
 *
 * @property eligibilityAgent An instance of the EligibilityAgent responsible for handling eligibility checks.
 * @property verificationAgent An instance of the VerificationAgent responsible for handling verification processes.
 * @property approvalAgent An instance of the ApprovalAgent responsible for handling approval workflows.
 * @property submitAgent An instance of the SubmitApplicationAgent responsible for handling application submissions.
 *
 * @throws IllegalArgumentException if an unknown agent name is provided when retrieving an agent.
 */
class AgentRegistry(
    val eligibilityAgent: EligibilityAgent,
    val verificationAgent: VerificationAgent,
    val approvalAgent: ApprovalAgent,
    val submitAgent: SubmitApplicationAgent,
    val documentCheckAgent: PassportDocumentCheckAgent,
    val policeVerificationAgent: PassportPoliceVerificationAgent,
    val passportApprovalAgent: PassportApprovalAgent,
    val passportSubmissionAgent: SubmitPassportApplicationAgent
) {

    fun getAgent(
        name: String
    ): WorkflowAgent {

        return when (name) {

            "ELIGIBILITY" ->
                eligibilityAgent

            "VERIFICATION" ->
                verificationAgent

            "APPROVAL" ->
                approvalAgent

            "SUBMIT" ->
                submitAgent

            "DOCUMENT_CHECK" ->
                documentCheckAgent

            "POLICE_VERIFICATION" ->
                policeVerificationAgent

            "PASSPORT_APPROVAL" ->
                passportApprovalAgent

            "PASSPORT_SUBMISSION" ->
                passportSubmissionAgent

            else -> throw IllegalArgumentException(
                "Unknown Agent"
            )
        }
    }
}