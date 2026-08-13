package com.example.governmentservicepoc.di

import com.example.governmentservicepoc.adk.AdkRunnerManager
import com.example.governmentservicepoc.data.repository.A2UiAgent
import com.example.governmentservicepoc.domain.agents.AgentRegistry
import com.example.governmentservicepoc.domain.agents.incomecertificate.ApprovalAgent
import com.example.governmentservicepoc.domain.agents.DynamicWorkflowEngine
import com.example.governmentservicepoc.domain.agents.incomecertificate.EligibilityAgent
import com.example.governmentservicepoc.domain.agents.incomecertificate.ServiceSelectionAgent
import com.example.governmentservicepoc.domain.agents.incomecertificate.SubmitApplicationAgent
import com.example.governmentservicepoc.domain.agents.incomecertificate.VerificationAgent
import com.example.governmentservicepoc.domain.agents.passport.PassportApprovalAgent
import com.example.governmentservicepoc.domain.agents.passport.PassportDocumentCheckAgent
import com.example.governmentservicepoc.domain.agents.passport.PassportPoliceVerificationAgent
import com.example.governmentservicepoc.domain.agents.passport.SubmitPassportApplicationAgent
import com.example.governmentservicepoc.domain.agents.ui.UiAgentOrchestrator

import com.example.governmentservicepoc.domain.repository.CitizenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AgentModule {

    @Provides
    fun provideEligibilityAgent() =
        EligibilityAgent()

    @Provides
    fun provideApprovalAgent() =
        ApprovalAgent()

    @Provides
    fun provideVerificationAgent(repository: CitizenRepository) =
        VerificationAgent(repository)

    @Provides
    fun provideSubmitAgent() = SubmitApplicationAgent()

    @Provides
    fun providePassportVerificationAgent(repository: CitizenRepository) =
        PassportDocumentCheckAgent(repository)

    @Provides
    fun providePassportPoliceVerificationAgent(repository: CitizenRepository) =
        PassportPoliceVerificationAgent(repository)

    @Provides
    fun providePassportApprovalAgent() = PassportApprovalAgent()

    @Provides
    fun provideSubmitPassportApplicationAgent() = SubmitPassportApplicationAgent()

    @Provides
    fun provideAgentRegistry(
        eligibilityAgent: EligibilityAgent,
        verificationAgent: VerificationAgent,
        approvalAgent: ApprovalAgent,
        submitAgent: SubmitApplicationAgent,
        passportPoliceVerificationAgent: PassportPoliceVerificationAgent,
        documentVerificationAgent: PassportDocumentCheckAgent,
        passportApprovalAgent: PassportApprovalAgent,
        submitPassportApplicationAgent: SubmitPassportApplicationAgent
    ) =
        AgentRegistry(
            eligibilityAgent,
            verificationAgent,
            approvalAgent,
            submitAgent,
            documentVerificationAgent,
            passportPoliceVerificationAgent,
            passportApprovalAgent,
            submitPassportApplicationAgent
        )

    @Provides
    fun provideDynamicWorkflowEngine(
        registry: AgentRegistry
    ) =
        DynamicWorkflowEngine(registry)

    @Provides
    @Singleton
    fun provideAdkRunnerManager(): AdkRunnerManager {
        return AdkRunnerManager()
    }

    @Provides
    @Singleton
    fun provideServiceSelectionAgent(
        runnerManager: AdkRunnerManager
    ): ServiceSelectionAgent {

        return ServiceSelectionAgent(
            runnerManager
        )
    }

    @Provides
    @Singleton
    fun provideUiAGentOrchestrator(
        a2UiAgent: A2UiAgent
    ): UiAgentOrchestrator {
        return UiAgentOrchestrator(a2UiAgent)
    }

    @Provides
    @Singleton
    fun provideA2UiAgent(
        runnerManager: AdkRunnerManager
    ): A2UiAgent {
        return A2UiAgent(runnerManager)
    }


}