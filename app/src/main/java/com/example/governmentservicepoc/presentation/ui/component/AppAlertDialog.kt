package com.example.governmentservicepoc.presentation.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.governmentservicepoc.presentation.state.SubmissionState

@Composable
fun AppAlertDialog(
    title: String,
    message: String,
    icon: ImageVector,
    iconTint: Color,
    buttonText: String = "OK",
    onConfirm: () -> Unit
) {
    AlertDialog(onDismissRequest = { }, title = {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon, contentDescription = title, tint = iconTint
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(title)
        }
    }, text = {
        if (title.contains("success", true)) {

            CopyableApplicationId(message)
        } else {
            Text(
                text = message,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp)
            )
        }
    }, confirmButton = {
        TextButton(
            onClick = onConfirm
        ) {
            Text(
                text = buttonText, fontSize = 16.sp
            )
        }
    })
}


@Composable
fun CopyableApplicationId(message: String) {
    val clipboardManager = LocalClipboardManager.current
    val applicationId = "Application ID: $message"

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = applicationId, fontSize = 16.sp
        )

        IconButton(
            onClick = {
                clipboardManager.setText(
                    AnnotatedString(message)
                )
            }) {
            Icon(
                imageVector = Icons.Default.ContentCopy, contentDescription = "Copy"
            )
        }
    }
}