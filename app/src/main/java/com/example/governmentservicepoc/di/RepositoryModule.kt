package com.example.governmentservicepoc.di

import com.example.governmentservicepoc.data.api.GovernmentApi
import com.example.governmentservicepoc.data.repository.CitizenRepositoryImpl
import com.example.governmentservicepoc.domain.repository.CitizenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(
    SingletonComponent::class
)
object RepositoryModule {

    @Provides
    fun provideCitizenRepository(
        api: GovernmentApi
    ): CitizenRepository {

        return CitizenRepositoryImpl(
            api
        )
    }


//    @Provides
//    fun provideUiParser() = UiParser()


//    @Provides
//    fun provideUiRepository(
//        @ApplicationContext context: Context, parser: UiParser
//    ): DynamicUiRepository {
//
//        return DynamicUiRepositoryImpl(
//            context, parser
//        )
//    }
}