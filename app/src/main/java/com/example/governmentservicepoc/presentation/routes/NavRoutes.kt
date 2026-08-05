package com.example.governmentservicepoc.presentation.routes

sealed class NavRoutes(
    val route: String
) {

    object Home :
        NavRoutes("home_screen")

    object IncomeCertificate :
        NavRoutes("income_certificate")

    object Passport :
        NavRoutes("passport")

    object DrivingLicense :
        NavRoutes("driving_license")

    object Pension :
        NavRoutes("pension")
}
