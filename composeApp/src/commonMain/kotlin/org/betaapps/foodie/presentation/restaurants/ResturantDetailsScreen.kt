package org.betaapps.foodie.presentation.restaurants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.skydoves.landscapist.coil3.CoilImage
import kotlinx.serialization.json.Json
import org.betaapps.foodie.data.model.Dish
import org.betaapps.foodie.data.model.Restaurant
import org.betaapps.foodie.presentation.dashboard.DashboardEvent
import org.betaapps.foodie.presentation.dashboard.DashboardViewModel
import org.betaapps.foodie.presentation.shoppingcart.ShoppingCartEvent
import org.betaapps.foodie.presentation.shoppingcart.ShoppingCartViewModel
import org.betaapps.foodie.presentation.utils.AppLogger
import org.koin.compose.getKoin

data class RestaurantDetailsScreen(
    val jsonRestaurant: String,
) : Screen {

    private val restaurant = Json.decodeFromString<Restaurant>(jsonRestaurant)

    @Composable
    override fun Content() {
        val viewModel: ShoppingCartViewModel = getKoin().get()
        val cartItems = viewModel.state.collectAsState().value.order


        Column(modifier = Modifier.fillMaxSize())
        {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Box {
                    CoilImage(
                        imageModel = { restaurant.imageUrl },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Box(
                        modifier = Modifier.matchParentSize()
                            .background(Color.Black.copy(alpha = .5f))
                    )

                    Text(
                        text = restaurant.name,
                        modifier = Modifier.align(Alignment.Center),
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.align(Alignment.BottomEnd)
                            .padding(8.dp)
                    ) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color.White)
                        Text(
                            text = restaurant.rating,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        Icon(Icons.Default.Timer, contentDescription = null, tint = Color.White)
                        Text(
                            text = restaurant.deliveryTime,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )

                    }
                }
            }

            LazyColumn {
                items(restaurant.dishes.size) { index ->
                    val amount = if (cartItems.isEmpty()) 0
                    else {
                        val cartItem = cartItems.find { it.item == restaurant.dishes[index] }
                        cartItem?.quantity ?: 0
                    }
                    FoodCard(
                        dish = restaurant.dishes[index],
                        onDecrease = {viewModel.onEvent(ShoppingCartEvent.RemoveItem(restaurant.dishes[index]))},
                        onIncrease = {viewModel.onEvent(ShoppingCartEvent.AddItem(restaurant.dishes[index]))},
                        amount =  amount
                    )

                }
                item {

                }
            }

        }
    }
}
