package org.betaapps.foodie.presentation.restaurants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import kotlinx.serialization.json.Json
import org.betaapps.foodie.data.model.Restaurant

data class RestaurantListScreen(
    val jsonRestaurants: String,
    ) : Screen {

    @Composable
    override fun Content() {
        val restaurants = Json.decodeFromString<List<Restaurant>>(jsonRestaurants)
        val navigator = LocalNavigator.current

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