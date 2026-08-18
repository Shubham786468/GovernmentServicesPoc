package com.example.governmentservicepoc.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.governmentservicepoc.presentation.ui.a2ui.DynamicFormScreen
import com.example.governmentservicepoc.presentation.routes.NavRoutes
import com.example.governmentservicepoc.presentation.ui.HomeScreen

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {

    navigation(
        startDestination = NavRoutes.Home.route,
        route = BottomNavItem.Home.route
    ) {

        composable(
            NavRoutes.Home.route
        ) {

            HomeScreen(
                onIncomeClick = { id ->
                    navController.navigate(
                        "${NavRoutes.IncomeCertificate.route}/$id"
                    )
                },
                onPassportClick = { id ->
                    navController.navigate(
                        "${NavRoutes.Passport.route}/$id"
                    )
                },
                onDrivingLicenseClick = { id ->
                    navController.navigate(
                        "${NavRoutes.DrivingLicense.route}/$id"
                    )
                },
                onPensionClick = { id ->
                    navController.navigate(
                        "${NavRoutes.Pension.route}/$id"
                    )
                }
            )
        }

        composable(
            route = "${NavRoutes.IncomeCertificate.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val input = backStackEntry.arguments?.getString("id")
            DynamicFormScreen(
                userInput = input ?: "",
                snackbarHostState = snackbarHostState
            )

        }

        composable(
            route = "${NavRoutes.Passport.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val input = backStackEntry.arguments?.getString("id")

            DynamicFormScreen(
                userInput = input ?: "",
                snackbarHostState = snackbarHostState
            )

        }

        composable(
            route = "${NavRoutes.DrivingLicense.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val input = backStackEntry.arguments?.getString("id")

            DynamicFormScreen(
                userInput = input ?: "",
                snackbarHostState = snackbarHostState
            )

        }

        composable(
            route = "${NavRoutes.Pension.route}/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val input = backStackEntry.arguments?.getString("id")

            DynamicFormScreen(
                userInput = input ?: "",
                snackbarHostState = snackbarHostState
            )

        }
    }
}