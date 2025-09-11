package org.betaapps.foodie.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import foodie.composeapp.generated.resources.Res
import foodie.composeapp.generated.resources.login_logo
import org.betaapps.foodie.domain.validation.InputValidationResult
import org.betaapps.foodie.presentation.dashboard.DashboardScreen
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.getKoin

class RegisterScreen : Screen {
    @Composable
    override fun Content() {

        val viewModel: RegisterScreenViewModel = getKoin().get()
        val state = viewModel.registrationState.collectAsState().value

        val email = viewModel.email.collectAsState().value
        val password = viewModel.password.collectAsState().value
        val fullName = viewModel.fullName.collectAsState().value
        val phoneNumber = viewModel.phoneNumber.collectAsState().value
        val address = viewModel.address.collectAsState().value

        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.login_logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(32.dp)
                    .size(128.dp)

            )
            Spacer(Modifier.height(64.dp))
            TextField(
                value = email.text,
                onValueChange = { viewModel.onEvent(RegisterScreenEvent.EmailChanged(it)) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            if (email.validationResult is InputValidationResult.Error) {
                Text(
                    text = email.validationResult.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(Modifier.height(8.dp))
            TextField(
                value = password.text,
                onValueChange = { viewModel.onEvent(RegisterScreenEvent.PasswordChanged(it)) },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            if (password.validationResult is InputValidationResult.Error) {
                Text(
                    text = password.validationResult.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(Modifier.height(16.dp))
            TextField(
                value = fullName.text,
                onValueChange = { viewModel.onEvent(RegisterScreenEvent.FullNameChanged(it)) },
                label = { Text("full name") },
                modifier = Modifier.fillMaxWidth()
            )
            if (fullName.validationResult is InputValidationResult.Error) {
                Text(
                    text = fullName.validationResult.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(Modifier.height(16.dp))
            TextField(
                value = phoneNumber.text,
                onValueChange = { viewModel.onEvent(RegisterScreenEvent.PhoneNumberChanged(it)) },
                label = { Text("phone number") },
                modifier = Modifier.fillMaxWidth()
            )
            if (phoneNumber.validationResult is InputValidationResult.Error) {
                Text(
                    text = phoneNumber.validationResult.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(Modifier.height(16.dp))

            TextField(
                value = address.text,
                onValueChange = { viewModel.onEvent(RegisterScreenEvent.AddressChanged(it)) },
                label = { Text("address") },
                modifier = Modifier.fillMaxWidth()
            )
            if (address.validationResult is InputValidationResult.Error) {
                Text(
                    text = address.validationResult.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(Modifier.height(16.dp))

            ElevatedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.onEvent(
                        RegisterScreenEvent.Register(email = email.text, password = password.text)
                    )
                }) {
                Text("register")
            }
        }

        when {
            state.loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface.copy(alpha = .7f)),
                    contentAlignment = Alignment.Center

                ) {
                    CircularProgressIndicator()
                }
            }

            state.firebaseUser != null -> {
                navigator.replace(DashboardScreen())
            }

            state.registrationError != null -> {
                AlertDialog(
                    onDismissRequest = {
                        viewModel.onEvent(RegisterScreenEvent.DismissAlertDialog)
                    },
                    title = {
                        Text(text = "Error")
                    },
                    text = {
                        Text(text = state.registrationError)
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.onEvent(RegisterScreenEvent.DismissAlertDialog)
                        }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}