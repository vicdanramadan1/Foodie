package org.betaapps.foodie.presentation.core.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.betaapps.foodie.domain.validation.InputValidationResult
import org.betaapps.foodie.domain.validation.Validator

@Composable
fun InputTextField(
    value: String,
    onValueChange: (String) -> Unit,
    validator: Validator<String>,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier,
) {

    var validationResult: InputValidationResult = remember(value) { InputValidationResult.Loading }

    Column(horizontalAlignment = Alignment.Start)
    {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(value)
                validationResult = validator.validate(value)
                            },
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            label = label,
            colors = TextFieldColors(
                focusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onSecondary,
                disabledTextColor = MaterialTheme.colorScheme.background,
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                cursorColor = MaterialTheme.colorScheme.primary,
                errorCursorColor = MaterialTheme.colorScheme.error,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = Color.Transparent,
                disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
                disabledIndicatorColor = MaterialTheme.colorScheme.onSecondary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                disabledLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                errorLabelColor = MaterialTheme.colorScheme.error,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                disabledLeadingIconColor = MaterialTheme.colorScheme.primary,
                errorLeadingIconColor = MaterialTheme.colorScheme.error,
                focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.primary,
                disabledTrailingIconColor = MaterialTheme.colorScheme.primary,
                errorTrailingIconColor = MaterialTheme.colorScheme.error,
                focusedPlaceholderColor = MaterialTheme.colorScheme.error,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.primary,
                disabledPlaceholderColor = MaterialTheme.colorScheme.primary,
                errorPlaceholderColor = MaterialTheme.colorScheme.error,
                errorContainerColor = MaterialTheme.colorScheme.error,
                errorIndicatorColor = MaterialTheme.colorScheme.error,
                errorPrefixColor = MaterialTheme.colorScheme.error,
                errorSuffixColor = MaterialTheme.colorScheme.error,
                errorSupportingTextColor = MaterialTheme.colorScheme.error,
                errorTextColor = MaterialTheme.colorScheme.error,
                focusedSupportingTextColor = MaterialTheme.colorScheme.primary,
                disabledSupportingTextColor = MaterialTheme.colorScheme.primary,
                unfocusedSupportingTextColor = MaterialTheme.colorScheme.primary,
                focusedPrefixColor = MaterialTheme.colorScheme.primary,
                disabledPrefixColor = MaterialTheme.colorScheme.primary,
                unfocusedPrefixColor = MaterialTheme.colorScheme.primary,
                focusedSuffixColor = MaterialTheme.colorScheme.primary,
                disabledSuffixColor = MaterialTheme.colorScheme.primary,
                unfocusedSuffixColor = MaterialTheme.colorScheme.primary,
                textSelectionColors = TextSelectionColors(
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    handleColor = MaterialTheme.colorScheme.primary
                ),
            ),
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        if (validationResult is InputValidationResult.Error) {
            Text(
                text = (validationResult as InputValidationResult.Error).message,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}