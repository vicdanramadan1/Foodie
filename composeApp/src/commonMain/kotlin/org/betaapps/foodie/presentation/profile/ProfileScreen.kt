package org.betaapps.foodie.presentation.profile


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import org.koin.compose.getKoin

class ProfileScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: ProfileScreenViewModel = getKoin().get()
        val state = viewModel.state.collectAsState().value
        Column(modifier = Modifier.fillMaxSize())
        {
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

                state.error != null -> {
                    AlertDialog(
                        onDismissRequest = {
                            viewModel.onEvent(ProfileScreenEvent.DismissAlertDialog)
                        },
                        text = {
                            Text(text = state.error)
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                viewModel.onEvent(ProfileScreenEvent.DismissAlertDialog)
                            }
                            ) {
                                Text("OK")
                            }
                        })
                }

                state.user != null -> {
                    Text(state.user.name)
                    Text(state.user.email)
                    Text(state.user.address)
                }
            }
        }
    }
}