package com.example.governmentservicepoc.presentation.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.governmentservicepoc.presentation.navigation.AppNavGraph
import com.example.governmentservicepoc.ui.theme.GovernmentServicePOCTheme

@Composable
fun GovernmentServiceApp() {

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val navController = rememberNavController()

    GovernmentServicePOCTheme {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentWindowInsets = WindowInsets.systemBars,
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
            bottomBar = {
                AppBottomBar(navController)
            }
        ) { innerPadding ->

            AppNavGraph(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        }
    }

}