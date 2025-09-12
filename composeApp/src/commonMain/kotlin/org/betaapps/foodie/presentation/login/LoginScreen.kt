package org.betaapps.foodie.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import foodie.composeapp.generated.resources.Res
import foodie.composeapp.generated.resources.login_logo
import org.betaapps.foodie.domain.validation.InputValidationResult
import org.betaapps.foodie.presentation.dashboard.DashboardScreen
import org.betaapps.foodie.presentation.login.composables.ForgotPasswordDialog
import org.betaapps.foodie.presentation.register.RegisterScreen
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.getKoin

object LoginScreen : Screen {
    @Composable
    override fun Content() {

        val viewModel: LoginViewModel = getKoin().get()
        val loginState by viewModel.loginState.collectAsState()
        val email by viewModel.email.collectAsState()
        val password by viewModel.password.collectAsState()

        val navigator = LocalNavigator.currentOrThrow
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(64.dp))
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
                onValueChange = { viewModel.onEvent(LoginScreenEvent.EmailChanged(it)) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            if (email.validationResult is InputValidationResult.Error) {
                Text(
                    text = (email.validationResult as InputValidationResult.Error).message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(Modifier.height(8.dp))
            TextField(
                value = password.text,
                onValueChange = { viewModel.onEvent(LoginScreenEvent.PasswordChanged(it)) },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            if (password.validationResult is InputValidationResult.Error) {
                Text(
                    text = (password.validationResult as InputValidationResult.Error).message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(Modifier.height(16.dp))
            ElevatedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.onEvent(
                        LoginScreenEvent.Login(
                            email = email.text,
                            password = password.text
                        )
                    )
                }) {
                Text("Login")
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Don't have an account? ",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.clickable { navigator.push(RegisterScreen()) }
                )
            }
            Text(
                text = "Forgot your password?",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.clickable { viewModel.onEvent(LoginScreenEvent.ShowForgotPasswordDialog) }
            )
        }

        when {
            loginState.loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface.copy(alpha = .7f)),
                    contentAlignment = Alignment.Center

                ) {
                    CircularProgressIndicator()
                }
            }

            loginState.firebaseUser != null -> {
                navigator.replace(DashboardScreen())
            }

            loginState.alertDialogMessage != null -> {
                AlertDialog(
                    onDismissRequest = {
                        viewModel.onEvent(LoginScreenEvent.DismissAlertDialog)
                    },
                    text = {
                        Text(text = loginState.alertDialogMessage!!)
                    },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.onEvent(LoginScreenEvent.DismissAlertDialog)
                        }) {
                            Text("OK")
                        }
                    }
                )
            }

            loginState.showForgotPasswordDialog -> {
                        ForgotPasswordDialog(
                            onSendClick = {viewModel.onEvent(LoginScreenEvent.SendPasswordByEmail(it))},
                            onCancelClick = {viewModel.onEvent(LoginScreenEvent.DismissAlertDialog)}
                        )
            }
        }
    }
}