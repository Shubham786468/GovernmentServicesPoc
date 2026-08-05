package com.example.governmentservicepoc.presentation.ui

import androidx.appfunctions.internal.Dispatchers
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.governmentservicepoc.domain.model.ChatMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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


//@Composable
//fun HomeScreen(
//    modifier: Modifier = Modifier,
//    onIncomeClick: () -> Unit,
//    onPassportClick: () -> Unit,
//    onDrivingLicenseClick: () -> Unit,
//    onPensionClick: () -> Unit
//) {
//
//    val messages = remember {
//        mutableStateListOf(
//            ChatMessage(
//                "👋 Hello! I'm your Government Services Assistant.",
//                true
//            ),
//            ChatMessage(
//                "I can guide you through applications, eligibility checks, and required documents.",
//                true
//            ),
//            ChatMessage(
//                "What would you like to do today?",
//                true
//            ),
//            ChatMessage(
//                "1️⃣ Apply for Income Certificate",
//                true
//            ),
//            ChatMessage(
//                "2️⃣ Apply for Passport",
//                true
//            ),
//            ChatMessage(
//                "3️⃣ Apply for Driving License",
//                true
//            ),
//            ChatMessage(
//                "4️⃣ Apply for Pension",
//                true
//            ),
//            ChatMessage(
//                "Please select an option below to begin the process.",
//                true
//            )
//        )
//    }
//    val scrollState = rememberScrollState()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .verticalScroll(scrollState)
//            .padding(16.dp),
//    ) {
//
//        LazyColumn(
//            modifier = Modifier.weight(1f),
//            verticalArrangement = Arrangement.spacedBy(5.dp)
//        ) {
//            items(messages) { message ->
//                ChatBubble(message)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        ServiceButton(
//            text = "📄 Apply for Income Certificate"
//        ) {
//            messages.add(
//                ChatMessage(
//                    "I want to apply for an Income Certificate.",
//                    false
//                )
//            )
//            messages.add(
//                ChatMessage(
//                    "Great! I'll help you apply for an Income Certificate. Let's begin.",
//                    true
//                )
//            )
//            onIncomeClick()
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        ServiceButton(
//            text = "🛂 Apply for Passport"
//        ) {
//            messages.add(
//                ChatMessage(
//                    "I want to apply for a Passport.",
//                    false
//                )
//            )
//            messages.add(
//                ChatMessage(
//                    "Great! I'll help you with your Passport application. Let's begin.",
//                    true
//                )
//            )
//            onPassportClick()
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        ServiceButton(
//            text = "🚗 Apply for Driving License"
//        ) {
//            messages.add(
//                ChatMessage(
//                    "I want to apply for a Driving License.",
//                    false
//                )
//            )
//            messages.add(
//                ChatMessage(
//                    "Great! I'll guide you through the Driving License application process.",
//                    true
//                )
//            )
//            onDrivingLicenseClick()
//        }
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        ServiceButton(
//            text = "🏦 Apply for Pension"
//        ) {
//            messages.add(
//                ChatMessage(
//                    "I want to apply for Pension.",
//                    false
//                )
//            )
//            messages.add(
//                ChatMessage(
//                    "Great! I'll help you with the Pension application process.",
//                    true
//                )
//            )
//            onPensionClick()
//        }
//    }
//}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onIncomeClick: (String) -> Unit,
    onPassportClick: (String) -> Unit,
    onDrivingLicenseClick: (String) -> Unit,
    onPensionClick: (String) -> Unit
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

    var userInput by remember {
        mutableStateOf("")
    }

    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.lastIndex)
        }
    }
    val scrollState = rememberScrollState()

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

                    when {
                        input.contains("income", true) -> {
                            scope.launch {
                                delay(1000.milliseconds)
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
                                onIncomeClick(input)
                            }
                        }

                        input.contains("passport", true) -> {
                            scope.launch {
                                delay(1000.milliseconds)
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
                                onPassportClick(input)
                            }
                        }

                        input.contains("driving", true) ||
                                input.contains("license", true) -> {
                            scope.launch {
                                delay(1000.milliseconds)
                                messages.add(
                                    ChatMessage(
                                        "Sure, I can help you apply for a Driving License. Let's begin.",
                                        true
                                    )
                                )
                                delay(1000.milliseconds)
                                messages.add(
                                    ChatMessage(
                                        "Please Wait while redirecting you to the Driving License Application Form...",
                                        true
                                    )
                                )

                                delay(3000.milliseconds)
                                onDrivingLicenseClick(input)
                            }

                        }

                        input.contains("pension", true) -> {
                            scope.launch {
                                delay(1000.milliseconds)
                                messages.add(
                                    ChatMessage(
                                        "Sure, I can help you apply for Pension. Let's begin.",
                                        true
                                    )
                                )
                                delay(1000.milliseconds)
                                messages.add(
                                    ChatMessage(
                                        "Please Wait while redirecting you to the Pension Application Form...",
                                        true
                                    )
                                )

                                delay(3000.milliseconds)
                                onPensionClick(input)
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
}

//@Composable
//fun ServiceButton(
//    text: String,
//    onClick: () -> Unit
//) {
//    Button(
//        modifier = Modifier.fillMaxWidth(),
//        onClick = onClick
//    ) {
//        Text(text)
//    }
//}