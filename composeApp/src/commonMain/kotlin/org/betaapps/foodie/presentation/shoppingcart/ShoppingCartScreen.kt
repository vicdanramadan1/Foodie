package org.betaapps.foodie.presentation.shoppingcart

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import org.betaapps.foodie.presentation.restaurants.FoodCard
import org.koin.compose.getKoin

class ShoppingCartScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ShoppingCartViewModel = getKoin().get()
        val state = viewModel.state.collectAsState().value
        val order = state.order

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

        ) {
            items(order.size)
            { index ->
                FoodCard(
                    dish = order[index].item,
                    amount = order[index].quantity,
                    onDecrease = {
                        viewModel.onEvent(ShoppingCartEvent.RemoveItem(it))
                        viewModel.onEvent(ShoppingCartEvent.CalculateTotalPrice)
                    },
                    onIncrease = {
                        viewModel.onEvent(ShoppingCartEvent.AddItem(it))
                        viewModel.onEvent(ShoppingCartEvent.CalculateTotalPrice)
                    }
                )
            }
            item{
                Text("total price : ${state.totalPrice}}")
            }
        }

    }
}
