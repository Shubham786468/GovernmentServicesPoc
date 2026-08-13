package com.example.governmentservicepoc.presentation.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.governmentservicepoc.presentation.ui.a2ui.model.A2UiComponent
import java.text.SimpleDateFormat
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicDateField(
    field: A2UiComponent,
    value: String,
    error: String? = null,
    onValueChange: (String) -> Unit
) {

    var showDatePicker by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()

    val activeColor = MaterialTheme.colorScheme.primary

    val iconColor by animateColorAsState(
        targetValue = if (showDatePicker) activeColor else Color.Gray,
        label = "IconColorAnimation"
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isFocused) {
            showDatePicker = true
            1.02f
        } else 1f,
        label = "scale"
    )


    OutlinedTextField(
        value = value,
        onValueChange = {},
        readOnly = true,
        label = {
            Text(field.label)
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    showDatePicker = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Select Date",
                    tint = iconColor
                )
            }
        },

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = activeColor,
            focusedLabelColor = activeColor,
            cursorColor = activeColor,
            focusedTrailingIconColor = activeColor,
            unfocusedTrailingIconColor = Color.Gray
        ),

        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                showDatePicker = true
            }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },

        isError = !error.isNullOrBlank(),

        supportingText = {
            error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )

    AnimatedVisibility(
        visible = showDatePicker,
        enter = fadeIn() + scaleIn(initialScale = 0.9f),
        exit = fadeOut() + scaleOut(targetScale = 0.9f)
    ) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->

                            val formattedDate =
                                SimpleDateFormat(
                                    "dd/MM/yyyy",
                                    Locale.getDefault()
                                ).format(Date(millis))

                            onValueChange(formattedDate)
                        }

                        showDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = activeColor,
                    todayDateBorderColor = activeColor,
                    selectedYearContainerColor = activeColor
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable()
fun Preview(modifier: Modifier = Modifier) {
    var date by remember {
        mutableStateOf("15/08/2026")
    }

    DynamicDateField(
        field = A2UiComponent(
            id = "dob",
            label = "Date of Birth",
            type = "date",
            errorValidation = null,
            required = true,
        ),
        value = date,
        error = null,
        onValueChange = {
            date = it
        }
    )
}

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DynamicDateField(
//    field: Field,
//    value: String,
//    error: String? = null,
//    onValueChange: (String) -> Unit
//) {
//    var showDatePicker by remember {
//        mutableStateOf(false)
//    }
//
//    val datePickerState = rememberDatePickerState()
//
//    OutlinedTextField(
//        value = value,
//        onValueChange = {},
//        readOnly = true,
//        label = {
//            Text(field.label)
//        },
//        trailingIcon = {
//            IconButton(
//                onClick = {
//                    showDatePicker = true
//                }
//            ) {
//                Icon(
//                    imageVector = Icons.Default.DateRange,
//                    contentDescription = "Select Date"
//                )
//            }
//        },
//        isError = !error.isNullOrBlank(),
//        supportingText = {
//            error?.let {
//                Text(
//                    text = it,
//                    color = MaterialTheme.colorScheme.error
//                )
//            }
//        },
//        modifier = Modifier.fillMaxWidth()
//    )
//
//    if (showDatePicker) {
//
//        DatePickerDialog(
//            onDismissRequest = {
//                showDatePicker = false
//            },
//            confirmButton = {
//                TextButton(
//                    onClick = {
//
//                        datePickerState.selectedDateMillis?.let { millis ->
//
//                            val formattedDate =
//                                SimpleDateFormat(
//                                    "dd/MM/yyyy",
//                                    Locale.getDefault()
//                                ).format(Date(millis))
//
//                            onValueChange(formattedDate)
//                        }
//
//                        showDatePicker = false
//                    }
//                ) {
//                    Text("OK")
//                }
//            },
//            dismissButton = {
//                TextButton(
//                    onClick = {
//                        showDatePicker = false
//                    }
//                ) {
//                    Text("Cancel")
//                }
//            }
//        ) {
//            DatePicker(
//                state = datePickerState
//            )
//        }
//    }
//}