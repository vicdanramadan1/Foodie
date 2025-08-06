package org.betaapps.foodie.presentation.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import org.betaapps.foodie.data.model.DishOrderItem
import org.betaapps.foodie.data.model.Dish
import org.betaapps.foodie.data.model.Order
import org.betaapps.foodie.data.model.OrderStatus
import org.betaapps.foodie.data.model.User
import org.betaapps.foodie.presentation.core.composables.SharedAppBar
import org.betaapps.foodie.presentation.home.HomeScreen
import org.betaapps.foodie.presentation.shoppingcart.ShoppingCartEvent
import org.betaapps.foodie.presentation.shoppingcart.ShoppingCartScreen
import org.betaapps.foodie.presentation.shoppingcart.ShoppingCartViewModel
import org.koin.compose.getKoin

class DashboardScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel: ShoppingCartViewModel = getKoin().get()
        val ordersNum = viewModel.state.collectAsState().value.order.sumOf { it.quantity }
        Navigator(HomeScreen())
        { navigator ->

            Scaffold(
                topBar = {
                    SharedAppBar(
                        basketItemsNumber = ordersNum,
                        navigator = navigator,
                        user = User(
                            id = "123",
                            name = "name",
                            email = "email",
                            order = Order(
                                id = "123/1",
                                userId = "123",
                                total = 24.0,
                                restaurantId = "",
                                status = OrderStatus.BLANK,
                                items = listOf(
                                    DishOrderItem(
                                        item = Dish(
                                            "1",
                                            "Pepperoni",
                                            description = "pepperoni pepperoni",
                                            "12.99",
                                            "https://firebasestorage.googleapis.com/v0/b/rentyourcar-446809.firebasestorage.app/o/Screenshot%202025-06-29%20at%2012.40.16.png?alt=media&token=3034ac32-f692-46f2-bc02-001bc068e9c3",
                                        ),
                                        quantity = 3,

                                        )
                                ),

                                ),
                            address = "",
                            favoriteRestaurants = emptyList()
                        )
                    )
                },
            )
            { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
                    CurrentScreen()
                }
            }

        }
    }

}