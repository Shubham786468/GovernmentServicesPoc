package com.example.governmentservicepoc.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.AccessAlarm
import androidx.compose.material.icons.outlined.AddTask
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.governmentservicepoc.presentation.state.DynamicUiState
import com.example.governmentservicepoc.presentation.state.SubmissionState
import com.example.governmentservicepoc.presentation.ui.component.AppAlertDialog
import com.example.governmentservicepoc.presentation.ui.component.DynamicCircularProgressIndicator
import com.example.governmentservicepoc.presentation.viewmodel.DynamicFormViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DynamicFormRoute(
    modifier: Modifier = Modifier,
    userInput: String,
    viewModel: DynamicFormViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        viewModel.loadScreen(userInput)
    }

    LaunchedEffect(Unit) {
        viewModel.snackbarMessage.collectLatest { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val state by viewModel.state.collectAsState()
        val submissionState by viewModel.submissionState.collectAsState()

        when (state) {

            is DynamicUiState.Loading -> {
                DynamicCircularProgressIndicator()
            }

            is DynamicUiState.Success -> {

                val metadata = (state as DynamicUiState.Success).metadata

                val formState by viewModel.formState.collectAsState()

                DynamicFormScreen(
                    modifier = modifier,
                    metadata = metadata,
                    values = formState.values,
                    errors = formState.errors,
                    onValueChanged = viewModel::updateField,
                    onSubmit = { action ->
                        viewModel.executeAction(
                            action.appFunction
                        )
                    })
            }

            is DynamicUiState.Error -> {
                Text(text = (state as DynamicUiState.Error).message)
            }
        }

        when (submissionState) {

            is SubmissionState.Loading -> {
                DynamicCircularProgressIndicator()
            }

            is SubmissionState.Success -> {

                val applicationId = (submissionState as SubmissionState.Success).applicationId

                AppAlertDialog(
                    title = "Success",
                    message = applicationId,
                    icon = Icons.Outlined.AddTask,
                    iconTint = Color.Green,
                ) {
                    viewModel.dismissDialog()
                }
            }

            is SubmissionState.Rejected -> {
                val rejectedMsg =
                    "Rejected : ${(submissionState as SubmissionState.Rejected).reason}"

                AppAlertDialog(
                    title = "Limit Exceed",
                    message = rejectedMsg,
                    icon = Icons.Outlined.AccessAlarm,
                    iconTint = Color.Red,
                ) {
                    viewModel.dismissDialog()
                }
            }

            is SubmissionState.Error -> {

                val errorMessage: String =
                    (submissionState as SubmissionState.Error).message

                AppAlertDialog(
                    title = "Error",
                    message = errorMessage,
                    icon = Icons.Default.Error,
                    iconTint = Color.Red,
                ) {
                    viewModel.dismissDialog()
                }
            }

            else -> {}
        }
    }


}