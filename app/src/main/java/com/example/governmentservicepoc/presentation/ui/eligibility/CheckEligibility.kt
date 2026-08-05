package com.example.governmentservicepoc.presentation.ui.eligibility

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.governmentservicepoc.presentation.ui.component.DynamicCircularProgressIndicator
import com.example.governmentservicepoc.presentation.viewmodel.EligibilityViewModel
import com.example.governmentservicepoc.utils.isValidIncome


@Composable
fun CheckEligibility(
    modifier: Modifier = Modifier,
    eligibilityViewModel: EligibilityViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()

    var userInput by remember {
        mutableStateOf("")
    }

    val listState = rememberLazyListState()
    val scroll = rememberScrollState()
    val isLoading by eligibilityViewModel.isLoading.collectAsState()
    val isEligible by eligibilityViewModel.isEligible.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(16.dp),
    ) {

        Text(
            text = "Check Eligibility",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = userInput,
                onValueChange = {
                    userInput = it
                },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text("Annual Income in a year. Ex: 50000")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {

                    }
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {

                    val input = userInput.trim()

                    if (input.isBlank()) return@IconButton
                    if (input.isValidIncome()) {
                        eligibilityViewModel.checkEligibility(input.toDoubleOrNull() ?: 0.0)
                    } else {
//                        eligibilityViewModel.updateSnackbarEvent("Please enter a valid income")
                    }

                    userInput = ""
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send"
                )
            }
        }


    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            DynamicCircularProgressIndicator()
        }
        isEligible?.let {
            Text(
                text = if (it) "✅ Eligible"
                else "❌ Not Eligible",
                color = if (it) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.error,

                style = MaterialTheme.typography.headlineMedium,
            )
        }
    }


}