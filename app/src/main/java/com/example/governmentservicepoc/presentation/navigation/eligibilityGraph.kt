package com.example.governmentservicepoc.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.governmentservicepoc.presentation.ui.eligibility.BaseEligibility
import com.example.governmentservicepoc.presentation.ui.eligibility.CheckEligibility
import com.example.governmentservicepoc.presentation.ui.eligibility.DocumentEligibility

fun NavGraphBuilder.eligibilityGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState
) {

    navigation(
        startDestination = "eligibility_screen",
        route = BottomNavItem.Eligibility.route
    ) {

        composable("eligibility_screen") {
            BaseEligibility(
                onEligibilityClick = {
                    navController.navigate(
                        "eligibility"
                    )
                },
                onDocumentClick = {
                    navController.navigate(
                        "document"
                    )
                }
            )


        }

        composable("eligibility") {
            CheckEligibility(snackbarHostState = snackbarHostState)
        }
        composable("document") {
            DocumentEligibility(snackbarHostState = snackbarHostState)
        }
    }
}