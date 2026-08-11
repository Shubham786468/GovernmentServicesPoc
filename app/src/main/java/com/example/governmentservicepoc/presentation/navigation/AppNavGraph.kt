package com.example.governmentservicepoc.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier
    ) {

        homeGraph(
            navController,
            snackbarHostState
        )

        checkStatusGraph(
            navController
        )

        eligibilityGraph(
            navController,
            snackbarHostState
        )
    }
}


//
//@Composable
//fun AppNavGraph(
//    modifier: Modifier,
//    navController: NavHostController,
//    snackbarHostState: SnackbarHostState
//) {
//
//    NavHost(
//        navController = navController,
//        startDestination = NavRoutes.Home.route,
//        modifier = modifier
//    ) {
//
//        composable(
//            NavRoutes.Home.route
//        ) {
//            HomeScreen(
//                onIncomeClick = {
//                    navController.navigate(
//                        NavRoutes
//                            .IncomeCertificate
//                            .route
//                    )
//                },
//                onPassportClick = {
//                    navController.navigate(
//                        NavRoutes
//                            .Passport.route
//                    )
//                },
//                onDrivingLicenseClick = {
//                    navController.navigate(
//                        NavRoutes
//                            .DrivingLicense
//                            .route
//                    )
//                },
//                onPensionClick = {
//                    navController.navigate(
//                        NavRoutes
//                            .Pension.route
//                    )
//                }
//            )
//        }
//
//        composable(
//            NavRoutes.IncomeCertificate.route
//        ) {
//            DynamicFormRoute(snackbarHostState = snackbarHostState)
//        }
//
//        composable(
//            NavRoutes.Passport.route
//        ) {
//            ComingSoonScreen(
//                title = "Passport"
//            )
//        }
//
//        composable(
//            NavRoutes.DrivingLicense.route
//        ) {
//            ComingSoonScreen(
//                title =
//                    "Driving License"
//            )
//        }
//
//        composable(
//            NavRoutes.Pension.route
//        ) {
//            ComingSoonScreen(
//                title = "Pension"
//            )
//        }
//    }
//}