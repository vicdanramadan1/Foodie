package org.betaapps.foodie.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import foodie.composeapp.generated.resources.Res
import foodie.composeapp.generated.resources.splashscreen
import kotlinx.coroutines.delay
import org.betaapps.foodie.presentation.home.HomeScreen
import org.betaapps.foodie.presentation.login.LoginScreen
import org.jetbrains.compose.resources.painterResource

class SplashScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        // Delay for splash (2 sec)
        LaunchedEffect(Unit) {
            delay(2000)
            navigator?.push(LoginScreen) // Replace with actual screen
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(Res.drawable.splashscreen),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}