import org.betaapps.foodie.data.model.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurants(): List<Restaurant>
    suspend fun getRestaurantDetails(id: String): Restaurant
}