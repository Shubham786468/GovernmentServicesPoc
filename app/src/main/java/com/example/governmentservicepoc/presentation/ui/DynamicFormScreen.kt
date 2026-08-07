package com.example.governmentservicepoc.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.governmentservicepoc.domain.model.ActionMetadata
import com.example.governmentservicepoc.domain.model.Field
import com.example.governmentservicepoc.domain.model.ScreenMetadata
import com.example.governmentservicepoc.presentation.ui.component.DynamicButton
import com.example.governmentservicepoc.utils.AppConstants
import com.example.governmentservicepoc.utils.getCertificateBackgroundImageUri

@Composable
fun DynamicFormScreen(
    modifier: Modifier,
    metadata: ScreenMetadata,
    values: Map<String, String>,
    errors: Map<String, String>,
//    onValueChanged: (String, String) -> Unit,
    onValueChanged: (Field, String) -> Unit,
    onSubmit: (ActionMetadata) -> Unit
) {

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.White, Color.White)
//                    colors = listOf(
//                        Color(0xE489CDFA),
//                        Color(0xCB8FD4FA),
//                        Color(0xD3AEDEF8),
//                    )
                ), shape = RoundedCornerShape(0.dp)
            )
            .padding(0.dp),
        contentAlignment = Alignment.Center
    ) {
        //Background Image
        ShowBackgroundIcon(metadata, modifier = modifier)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {

            Text(
                text = metadata.title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            FormRenderer(
                fields = metadata.fields,
                values = values,
                errors = errors,
                onValueChanged = onValueChanged
            )

            metadata.actions.forEach { action ->
                if (action.type == AppConstants.PRIMARY_BUTTON_TYPE)
                    DynamicButton(label = action.label) {
                        onSubmit(action)
                    }
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )
        }

    }
}

@Composable
fun ShowBackgroundIcon(metadata: ScreenMetadata, modifier: Modifier = Modifier) {

    val ctx = LocalContext.current
    metadata.screenBackgroundUrl = metadata.screenId.getCertificateBackgroundImageUri(ctx)

    metadata.screenBackgroundUrl?.let { url ->
        Card(
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = url,
                onError = {
                    print("url fail")
                },
                onLoading = {
                    print("url loading")
                },
                onSuccess = {
                    print("url loaded successfully")
                },
                contentScale = ContentScale.Fit,
                contentDescription = "${metadata.title} Background Image",
                modifier = modifier
                    .background(color = Color.White)
                    .fillMaxSize()
                    .alpha(0.5f)
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewShowBackgroudnIcon(modifier: Modifier = Modifier) {
//    ShowBackgroundIcon(
//        metadata = ScreenMetadata(
//            title = "Sample Form",
//            screenId = "sample_form_screen",
//            screenBackgroundUrl = "https://images.rawpixel.com/image_png_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTAzL3JtNjQwLWVsZW1lbnQtMDMwLnBuZw.png",
//            workflow = listOf("submit_form"),
//            fields = listOf(
//                Field(
//                    id = "dob", label = "Date of Birth", type = "date", errorValidation = null
//                )
//            ),
//            actions = listOf(
//                ActionMetadata(
//                    label = "Submit",
//                    type = AppConstants.PRIMARY_BUTTON_TYPE,
//                    appFunction = "submit_form",
//                    id = "submit_form_action",
//                )
//            )
//        )
//    )
}


//@Preview(showBackground = true)
@Composable()
fun Preview(modifier: Modifier = Modifier) {
//    var date by remember {
//        mutableStateOf("15/08/2026")
//    }

//    DynamicFormScreen(
//        modifier = modifier, metadata = ScreenMetadata(
//            title = "Sample Form",
//            screenId = "sample_form_screen",
//            screenBackgroundUrl = null,
//            workflow = listOf("submit_form"),
//            fields = listOf(
//                Field(
//                    id = "dob", label = "Date of Birth", type = "date", errorValidation = null
//                )
//            ),
//            actions = listOf(
//                ActionMetadata(
//                    label = "Submit",
//                    type = AppConstants.PRIMARY_BUTTON_TYPE,
//                    appFunction = "submit_form",
//                    id = "submit_form_action",
//                )
//            )
//        ), values = mapOf(
//            "date" to date, "text" to "Sample Text", "number" to "123", "dropdown" to "Option 1"
//        ), errors = emptyMap(), onValueChanged = { field, value ->
//            if (field.id == "dob") {
//                date = value
//            }
//        }, onSubmit = { action ->
//            // Handle form submission
//        })

}

