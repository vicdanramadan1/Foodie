package org.betaapps.foodie.presentation.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class ProfileScreen: Screen{

    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize())
        {
            Text("profile")
        }
    }

}