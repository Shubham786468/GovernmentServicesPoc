package com.example.governmentservicepoc.presentation.ui.eligibility

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.governmentservicepoc.domain.model.Validation
import com.example.governmentservicepoc.presentation.ui.component.DynamicCircularProgressIndicator
import com.example.governmentservicepoc.presentation.viewmodel.DocumentEligibilityViewModel
import com.example.governmentservicepoc.utils.isValidAadhaar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DocumentEligibility(
    modifier: Modifier = Modifier,
    eligibilityViewModel: DocumentEligibilityViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {

    LaunchedEffect(Unit) {
        eligibilityViewModel.snackbarMessage.collectLatest { message ->
            snackbarHostState.showSnackbar(message)
        }
    }


    var userInput by remember {
        mutableStateOf("")
    }

    val listState = rememberLazyListState()
    val scroll = rememberScrollState()
    val scope = rememberCoroutineScope()



    val isLoading by eligibilityViewModel.isLoading.collectAsState()
    val isEligible by eligibilityViewModel.isEligible.collectAsState()
    val error by eligibilityViewModel.error.collectAsState()


    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()



    val scale by animateFloatAsState(
        targetValue = if (isFocused) 1.02f else 1f,
        label = "scale"
    )

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
                placeholder = {
                    Text("Aadhaar Number...")
                },
                interactionSource = interactionSource,
                isError = error != null,
                supportingText = {
                    error?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {

                    }
                ),
                modifier = Modifier
                    .weight(1f)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                modifier = Modifier.padding(bottom = 17.dp),
                onClick = {
                    val input = userInput.trim()

                    if (input.isBlank()) return@IconButton
                    if (input.isValidAadhaar()) {
                        eligibilityViewModel.checkEligibility(input)
                    } else {
                        eligibilityViewModel.updateSnackbarEvent("Please enter a valid Aadhaar Number")
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