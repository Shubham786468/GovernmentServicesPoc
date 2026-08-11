package com.example.governmentservicepoc.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.governmentservicepoc.domain.model.ChatMessage
import com.example.governmentservicepoc.presentation.state.DynamicUiState
import com.example.governmentservicepoc.presentation.ui.component.WaveTypingIndicator
import com.example.governmentservicepoc.presentation.viewmodel.HomeScreenViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.compose
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

//
//@Composable
//fun HomeScreen(
//    modifier: Modifier = Modifier,
//    onIncomeClick: () -> Unit,
//    onPassportClick: () -> Unit,
//    onDrivingLicenseClick: () -> Unit,
//    onPensionClick: () -> Unit
//) {
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(24.dp),
//        verticalArrangement =
//            Arrangement.spacedBy(16.dp),
//        horizontalAlignment =
//            Alignment.CenterHorizontally
//    ) {
//
//        Text(
//            text = "Government Services",
//            style = MaterialTheme.typography.headlineMedium
//        )
//
//        Spacer(Modifier.size(10.dp))
//
//        Button(
//            modifier =
//                Modifier.fillMaxWidth(),
//            onClick =
//                onIncomeClick
//        ) {
//
//            Text(
//                text = "Income Certificate", fontSize = 16.sp,
//                modifier = Modifier.padding(10.dp)
//            )
//        }
//
//        Button(
//            modifier =
//                Modifier.fillMaxWidth(),
//            onClick =
//                onPassportClick
//        ) {
//            Text(
//                text = "Passport", fontSize = 16.sp,
//                modifier = Modifier.padding(10.dp)
//            )
//        }
//
//        Button(
//            modifier =
//                Modifier.fillMaxWidth(),
//            onClick =
//                onDrivingLicenseClick
//        ) {
//            Text(
//                text = "Driving License", fontSize = 16.sp,
//                modifier = Modifier.padding(10.dp)
//            )
//        }
//
//        Button(
//            modifier =
//                Modifier.fillMaxWidth(),
//            onClick =
//                onPensionClick
//        ) {
//            Text(
//                text = "Pension", fontSize = 16.sp,
//                modifier = Modifier.padding(10.dp)
//            )
//        }
//    }
//}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onIncomeClick: (String) -> Unit,
    onPassportClick: (String) -> Unit,
    onDrivingLicenseClick: (String) -> Unit,
    onPensionClick: (String) -> Unit,
    homeViewModel: HomeScreenViewModel = hiltViewModel()
) {

    val messages = remember {
        mutableStateListOf(
            ChatMessage(
                "👋 Hello! I'm your Government Services Assistant.",
                true
            ),
            ChatMessage(
                "How can I help you today?",
                true
            )
        )
    }


    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var userInput by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    val error by homeViewModel.error.collectAsState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }


    LaunchedEffect(Unit) {
        homeViewModel.serviceName.collect { service ->
            messages.remove(ChatMessage("", true, true))
            when (service.lowercase()) {
                "income_certificate" -> {
                    scope.launch {
                        delay(500.milliseconds)
                        messages.add(
                            ChatMessage(
                                "Sure, I can help you apply for an Income Certificate.",
                                true
                            )
                        )
                        delay(1000.milliseconds)
                        messages.add(
                            ChatMessage(
                                "Please Wait while redirecting you to the Income Certificate Application Form...",
                                true
                            )
                        )

                        delay(2.seconds)
                        onIncomeClick(service)
                    }
                }

                "passport" -> {
                    scope.launch {
                        delay(500.milliseconds)
                        messages.add(
                            ChatMessage(
                                "Sure, I can help you apply for a Passport. Let's begin.",
                                true
                            )
                        )
                        delay(1000.milliseconds)
                        messages.add(
                            ChatMessage(
                                "Please Wait while redirecting you to the Passport Application Form...",
                                true
                            )
                        )


                        delay(3000.milliseconds)
                        onPassportClick(service)
                    }
                }

                "pension" -> {
                    scope.launch {
                        delay(500.milliseconds)
                        messages.add(
                            ChatMessage(
                                "Sorry for the inconvenience, Not available for Now.",
                                true
                            )
                        )
                    }
                }

                "driving_license" -> {
                    scope.launch {
                        delay(500.milliseconds)
                        messages.add(
                            ChatMessage(
                                "Sorry for the inconvenience, Not available for Now.",
                                true
                            )
                        )
                    }
                }

                else -> {
                    messages.add(
                        ChatMessage(
                            "I can assist with Income Certificate, Passport, Driving License, and Pension services. Please tell me which service you'd like to use.",
                            true
                        )
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { message ->
                if (message.isLoading == true) {
                    AnimatedVisibility(
                        visible = homeViewModel.isLoading.collectAsState().value
                    ) {
                        WaveTypingIndicator()
                    }
                } else
                    ChatBubble(message)
            }
        }

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
                    Text("Type your request...")
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

                    messages.add(
                        ChatMessage(
                            input,
                            false
                        )
                    )

                    if (input.isNotEmpty() &&
                        (input.trim().lowercase() == "hi" || input.trim().lowercase() == "hello" || input.trim().lowercase() == "hey")
                    ) {
                        messages.add(
                            ChatMessage(
                                "Hi, How can I help you?",
                                true
                            )
                        )
                    } else {
                        messages.add(ChatMessage("", true, true))
                        homeViewModel.getServiceName(input)
                    }

                    userInput = ""

//                    when (serviceName.trim().lowercase()) {
//
//                        "income", "income_certificate" -> {
//                            scope.launch {
//                                delay(1000.milliseconds)
//                                messages.add(
//                                    ChatMessage(
//                                        "Sure, I can help you apply for an Income Certificate.",
//                                        true
//                                    )
//                                )
//                                delay(1000.milliseconds)
//                                messages.add(
//                                    ChatMessage(
//                                        "Please Wait while redirecting you to the Income Certificate Application Form...",
//                                        true
//                                    )
//                                )
//
//                                delay(2.seconds)
//                                onIncomeClick(input)
//                            }
//                        }
//
//
//                        "passport" -> {
//                            scope.launch {
//                                delay(1000.milliseconds)
//                                messages.add(
//                                    ChatMessage(
//                                        "Sure, I can help you apply for a Passport. Let's begin.",
//                                        true
//                                    )
//                                )
//                                delay(1000.milliseconds)
//                                messages.add(
//                                    ChatMessage(
//                                        "Please Wait while redirecting you to the Passport Application Form...",
//                                        true
//                                    )
//                                )
//
//
//                                delay(3000.milliseconds)
//                                onPassportClick(input)
//                            }
//                        }
//
//                        "pension" -> {
//                            scope.launch {
//                                delay(1000.milliseconds)
//                                messages.add(
//                                    ChatMessage(
//                                        "Sorry for the inconvenience, Not available for Now.",
//                                        true
//                                    )
//                                )
//                            }
//                        }
//
//                        "driving_license" -> {
//                            scope.launch {
//                                delay(1000.milliseconds)
//                                messages.add(
//                                    ChatMessage(
//                                        "Sorry for the inconvenience, Not available for Now.",
//                                        true
//                                    )
//                                )
//                            }
//                        }
//
////                        input.contains("income", true) -> {
////                            scope.launch {
////                                delay(1000.milliseconds)
////                                messages.add(
////                                    ChatMessage(
////                                        "Sure, I can help you apply for an Income Certificate.",
////                                        true
////                                    )
////                                )
////                                delay(1000.milliseconds)
////                                messages.add(
////                                    ChatMessage(
////                                        "Please Wait while redirecting you to the Income Certificate Application Form...",
////                                        true
////                                    )
////                                )
////
////                                delay(2.seconds)
////                                onIncomeClick(input)
////                            }
////                        }
////
////                        input.contains("passport", true) -> {
////                            scope.launch {
////                                delay(1000.milliseconds)
////                                messages.add(
////                                    ChatMessage(
////                                        "Sure, I can help you apply for a Passport. Let's begin.",
////                                        true
////                                    )
////                                )
////                                delay(1000.milliseconds)
////                                messages.add(
////                                    ChatMessage(
////                                        "Please Wait while redirecting you to the Passport Application Form...",
////                                        true
////                                    )
////                                )
////
////
////                                delay(3000.milliseconds)
////                                onPassportClick(input)
////                            }
////                        }
////
////                        input.contains("driving", true) ||
////                                input.contains("license", true) -> {
////                            scope.launch {
////                                delay(1000.milliseconds)
////                                messages.add(
////                                    ChatMessage(
////                                        "Sure, I can help you apply for a Driving License. Let's begin.",
////                                        true
////                                    )
////                                )
////                                delay(1000.milliseconds)
////                                messages.add(
////                                    ChatMessage(
////                                        "Please Wait while redirecting you to the Driving License Application Form...",
////                                        true
////                                    )
////                                )
////
////                                delay(3000.milliseconds)
////                                onDrivingLicenseClick(input)
////                            }
////
////                        }
////
////                        input.contains("pension", true) -> {
////                            scope.launch {
////                                delay(1000.milliseconds)
////                                messages.add(
////                                    ChatMessage(
////                                        "Sure, I can help you apply for Pension. Let's begin.",
////                                        true
////                                    )
////                                )
////                                delay(1000.milliseconds)
////                                messages.add(
////                                    ChatMessage(
////                                        "Please Wait while redirecting you to the Pension Application Form...",
////                                        true
////                                    )
////                                )
////
////                                delay(3000.milliseconds)
////                                onPensionClick(input)
////                            }
////                        }
//
//                        else -> {
//                            messages.add(
//                                ChatMessage(
//                                    "I can assist with Income Certificate, Passport, Driving License, and Pension services. Please tell me which service you'd like to use.",
//                                    true
//                                )
//                            )
//                        }
//                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send"
                )
            }
        }
    }


    if (error.isNotEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center,

            ) {
            error?.let { message ->
                Text(text = message)
            }

        }
    }
}
