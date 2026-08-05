package com.example.governmentservicepoc.presentation.ui.status

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.governmentservicepoc.presentation.ui.component.DynamicCircularProgressIndicator
import com.example.governmentservicepoc.presentation.viewmodel.CheckStatusViewModel


@Composable
fun CheckStatus(
    modifier: Modifier = Modifier, checkStatusViewModel: CheckStatusViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()

    var appId by remember { mutableStateOf("") }

    val status by checkStatusViewModel.status.collectAsState()
    val isLoading by checkStatusViewModel.isLoading.collectAsState()

    val listState = rememberLazyListState()

    val scroll = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scroll)
            .padding(16.dp),
    ) {

        Text(
            text = "Check Status",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = appId,
                onValueChange = {
                    appId = it
                }, modifier = Modifier.weight(1f),
                placeholder = {
                    Text("Application ID ex. APP-2026-01")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {

                    })
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {

                    val applicationId = appId.trim()

                    if (applicationId.isBlank()) return@IconButton


                    checkStatusViewModel.checkStatus(applicationId)

                    appId = ""
                }) {
                Icon(
                    imageVector = Icons.Default.Send, contentDescription = "Send"
                )
            }
        }


    }

    Box(
        modifier = Modifier
            .fillMaxSize() ,
        contentAlignment = Alignment.Center
    ) {

        if (isLoading) {
            DynamicCircularProgressIndicator()
        }

        status?.let {
            Text(
                text = "Application Id : ${checkStatusViewModel.getApplicationId()} \nApplication Status: $it",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    }

}

