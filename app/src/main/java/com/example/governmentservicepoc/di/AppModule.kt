package com.example.governmentservicepoc.di

import android.content.Context
import com.example.governmentservicepoc.data.repository.MetadataRepositoryImpl
import com.example.governmentservicepoc.domain.agents.DynamicWorkflowEngine
import com.example.governmentservicepoc.domain.agents.incomecertificate.EligibilityAgent
import com.example.governmentservicepoc.domain.agents.incomecertificate.ServiceSelectionAgent
import com.example.governmentservicepoc.domain.agents.passport.PassportDocumentCheckAgent
import com.example.governmentservicepoc.domain.agents.passport.PassportPoliceVerificationAgent
import com.example.governmentservicepoc.domain.repository.CitizenRepository
import com.example.governmentservicepoc.domain.repository.MetadataRepository
import com.example.governmentservicepoc.domain.usecase.CheckStatusUseCase
import com.example.governmentservicepoc.domain.usecase.EligibilityUseCase
import com.example.governmentservicepoc.domain.usecase.HomeServiceSelectionUseCase
//import com.example.governmentservicepoc.domain.usecase.LoadMetadataUseCase
import com.example.governmentservicepoc.domain.usecase.SubmitApplicationUseCase
import com.example.governmentservicepoc.domain.usecase.passport.CheckPassportStatusUseCase
import com.example.governmentservicepoc.domain.usecase.passport.SchedulePoliceVerificationUseCase
import com.example.governmentservicepoc.domain.usecase.passport.SubmitPassportUseCase
import com.example.governmentservicepoc.domain.usecase.passport.VerifyDocumentsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMetadataRepository(
        @ApplicationContext
        context: Context
    ): MetadataRepository {

        return MetadataRepositoryImpl(
            context
        )
    }

    @Provides
    fun provideSubmitUseCase(
        repository: CitizenRepository,
        workflow: DynamicWorkflowEngine
    ): SubmitApplicationUseCase {

        return SubmitApplicationUseCase(
            repository,
            workflow
        )
    }

    @Provides
    fun provideEligibilityUseCase(
        eligibilityAgent: EligibilityAgent
    ): EligibilityUseCase {

        return EligibilityUseCase(
            eligibilityAgent
        )
    }

    @Provides
    fun provideCheckStatusUseCase() = CheckStatusUseCase()

    @Provides
    fun provideCheckPassportStatusUseCase() = CheckPassportStatusUseCase()

    @Provides
    fun provideSubmitPassportUseCase(
        repository: CitizenRepository,
        workflow: DynamicWorkflowEngine
    ): SubmitPassportUseCase {

        return SubmitPassportUseCase(
            repository,
            workflow
        )
    }


    @Provides
    fun providePassportDocumentVerificationUseCase(
        passportDocumentCheckAgent: PassportDocumentCheckAgent
    ): VerifyDocumentsUseCase {

        return VerifyDocumentsUseCase(
            passportDocumentCheckAgent
        )
    }

    @Provides
    fun provideSchedulePoliceVerificationUseCase(
        schedulePoliceVerificationAgent: PassportPoliceVerificationAgent
    ): SchedulePoliceVerificationUseCase {

        return SchedulePoliceVerificationUseCase(
            schedulePoliceVerificationAgent
        )
    }

    @Provides
    fun provideHomeServiceSelectionUseCase(
        selectionAgent: ServiceSelectionAgent
    ): HomeServiceSelectionUseCase {
        return HomeServiceSelectionUseCase(
            selectionAgent
        )
    }

}