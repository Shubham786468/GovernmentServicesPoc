package com.example.governmentservicepoc.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomNavItem(
        "home_graph",
        "Home",
        Icons.Default.Home
    )

    data object Status : BottomNavItem(
        "check_status",
        "Status",
        Icons.Default.Apps
    )

    data object Eligibility : BottomNavItem(
        "check_eligibility",
        "Eligibility",
        Icons.Default.Person
    )
}