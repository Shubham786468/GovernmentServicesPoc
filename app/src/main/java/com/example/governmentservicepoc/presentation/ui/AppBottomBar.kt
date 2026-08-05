package com.example.governmentservicepoc.presentation.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.governmentservicepoc.presentation.navigation.BottomNavItem

@Composable
fun AppBottomBar(
    navController: NavHostController
) {

    val items = listOf(
        BottomNavItem.Status,
        BottomNavItem.Home,
        BottomNavItem.Eligibility
    )

    NavigationBar {

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentDestination = navBackStackEntry?.destination

        items.forEach { item ->

            NavigationBarItem(
                selected = currentDestination
                    ?.hierarchy
                    ?.any {
                        it.route == item.route
                    } == true,

                onClick = {

                    navController.navigate(item.route) {

                        popUpTo(
                            navController
                                .graph
                                .findStartDestination()
                                .id
                        ) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        item.icon,
                        null
                    )
                },

                label = {
                    Text(item.title)
                }
            )
        }
    }


}