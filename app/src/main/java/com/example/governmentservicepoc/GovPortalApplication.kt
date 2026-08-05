package com.example.governmentservicepoc

import android.app.Application
import android.content.SharedPreferences
import androidx.appfunctions.service.AppFunctionConfiguration
import com.example.governmentservicepoc.appfunctions.CitizenServiceAppFunctions
import com.example.governmentservicepoc.appfunctions.PassportServiceAppFunctions
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Provider

@HiltAndroidApp
class GovPortalApplication : Application(), AppFunctionConfiguration.Provider {

    @Inject
    lateinit var citizenServiceAppFunctions: Provider<CitizenServiceAppFunctions>

    @Inject
    lateinit var passportServiceAppFunctions: Provider<PassportServiceAppFunctions>

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override val appFunctionConfiguration: AppFunctionConfiguration =
        AppFunctionConfiguration.Builder()
            .addEnclosingClassFactory(CitizenServiceAppFunctions::class.java) { citizenServiceAppFunctions.get() }
            .addEnclosingClassFactory(PassportServiceAppFunctions::class.java) { passportServiceAppFunctions.get() }
            .build()
}