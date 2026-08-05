package com.example.governmentservicepoc.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.governmentservicepoc.presentation.ui.ComingSoonScreen
import com.example.governmentservicepoc.presentation.ui.status.CheckStatus

fun NavGraphBuilder.checkStatusGraph(
    navController: NavHostController
) {

    navigation(
        startDestination = "check_Status_screen",
        route = BottomNavItem.Status.route
    ) {

        composable("check_Status_screen") {

//            ComingSoonScreen("Check Status")
            CheckStatus()
        }

        composable("passport_details") {
//            PassportDetailsScreen()
        }
    }
}