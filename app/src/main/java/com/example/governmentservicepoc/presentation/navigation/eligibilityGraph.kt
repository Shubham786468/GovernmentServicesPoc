package com.example.governmentservicepoc.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.governmentservicepoc.presentation.ui.ComingSoonScreen
import com.example.governmentservicepoc.presentation.ui.eligibility.CheckEligibility

fun NavGraphBuilder.eligibilityGraph(
    navController: NavHostController
) {

    navigation(
        startDestination = "eligibility_screen",
        route = BottomNavItem.Eligibility.route
    ) {

        composable("eligibility_screen") {
//            ComingSoonScreen("Check Eligibility")
            CheckEligibility()
        }

        composable("settings") {
//            SettingsScreen()
        }
    }
}