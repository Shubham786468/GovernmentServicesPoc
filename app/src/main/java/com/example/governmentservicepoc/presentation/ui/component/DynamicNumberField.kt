package com.example.governmentservicepoc.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.governmentservicepoc.domain.model.Field

//@Composable
//fun DynamicNumberField(
//    field: Field,
//    value: String,
//    onValueChange: (String) -> Unit
//) {
//
//    OutlinedTextField(
//        value = value,
//        onValueChange = onValueChange,
//        keyboardOptions = KeyboardOptions(
//            keyboardType = KeyboardType.Number
//        ),
//        label = {
//            Text(field.label)
//        },
//        modifier = Modifier.fillMaxWidth()
//    )
//}

@Composable
fun DynamicNumberField(
    field: Field,
    value: String,
    error: String? = null,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        label = {
            Text(field.label)
        },
        isError = !error.isNullOrBlank(),
        supportingText = {
            error?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}