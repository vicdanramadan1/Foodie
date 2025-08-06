package org.betaapps.foodie


import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import org.betaapps.foodie.di.appModule
import org.betaapps.foodie.presentation.dashboard.DashboardScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.betaapps.foodie.theme.FoodAppTheme
import org.koin.core.context.startKoin

@Composable
@Preview
fun App() {
    FoodAppTheme {
        startKoin { modules(appModule) }
        Navigator(
            DashboardScreen(),
            disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
        )
    }
}