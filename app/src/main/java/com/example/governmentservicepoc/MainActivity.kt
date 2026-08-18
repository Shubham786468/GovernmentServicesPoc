package com.example.governmentservicepoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.governmentservicepoc.presentation.ui.GovernmentServiceApp
import com.example.governmentservicepoc.ui.theme.GovernmentServicePOCTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GovernmentServicePOCTheme {
                GovernmentServiceApp()
//                val snackbarHostState = remember { SnackbarHostState() }
////
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//                    contentWindowInsets = WindowInsets.systemBars,
//                    snackbarHost = {
//                        SnackbarHost(hostState = snackbarHostState)
//
//                    }
//                ) { innerPadding ->

//                    DynamicFormScreen(Modifier.padding(innerPadding))

//
//                    AppNavGraph(Modifier.padding(innerPadding),
//                        snackbarHostState=snackbarHostState)
////                    DynamicFormRoute(
////                        Modifier.padding(innerPadding)
////                    )
//                }
            }
        }
    }
}





