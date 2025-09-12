package org.betaapps.foodie.presentation.login.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun ForgotPasswordDialog(
    onSendClick: (String) -> Unit,
    onCancelClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onCancelClick,
        title = { Text("Forgot Password") },
        text = {
            Column {
                Text("Enter your email to reset password")
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onSendClick(email) }) {
                Text("Send")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelClick) {
                Text("Cancel")
            }
        }
    )
}