package org.betaapps.foodie.presentation.login

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import foodie.composeapp.generated.resources.Res
import foodie.composeapp.generated.resources.login_logo
import org.betaapps.foodie.presentation.dashboard.DashboardScreen
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.getKoin

object LoginScreen : Screen {
    @Composable
    override fun Content() {

        val viewModel: LoginViewModel = getKoin().get()
        val loginState by viewModel.loginState.collectAsState()
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
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
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            ElevatedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.onEvent(LoginScreenEvent.Login(email = email, password = password))
                }) {
                Text("Login")
            }

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

            loginState.error != null -> {
                AlertDialog(
                    onDismissRequest = {
                        viewModel.onEvent(LoginScreenEvent.DismissAlertDialog)
                    },
                    title = {
                        Text(text = "Error")
                    },
                    text = {
                        Text(text = loginState.error!!)
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
        }
    }

}