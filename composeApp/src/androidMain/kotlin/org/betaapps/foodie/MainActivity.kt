package org.betaapps.foodie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.FirebaseApp
import org.betaapps.foodie.di.initKoin
import org.betaapps.foodie.presentation.utils.AppLogger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
             setContent {
App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    FoodieApp()
}