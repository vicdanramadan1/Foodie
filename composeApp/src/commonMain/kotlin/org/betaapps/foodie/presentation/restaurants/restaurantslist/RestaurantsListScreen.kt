package org.betaapps.foodie.presentation.restaurants.restaurantslist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.serialization.json.Json
import org.betaapps.foodie.presentation.restaurants.RestaurantCard
import org.betaapps.foodie.presentation.restaurants.RestaurantDetailsScreen
import org.betaapps.foodie.presentation.utils.AppLogger
import org.koin.compose.getKoin

 class RestaurantListScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel: RestaurantsListScreenViewModel = getKoin().get()
        val state = viewModel.restaurants.collectAsState().value
        val restaurants = state.restaurants
        LazyColumn {
            items(restaurants.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp), elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    RestaurantCard(
                        restaurant = restaurants[index],
                        onClick = {
                            AppLogger.i("dishes" , restaurants[index].dishes.toString())
                            val restaurant = Json.encodeToString(restaurants[index])
                            navigator?.push(
                                    RestaurantDetailsScreen(
                                        jsonRestaurant = restaurant,
                                    )
                                )
                        }
                    )
                }
            }
        }
    }
}