import org.betaapps.foodie.data.model.Restaurant

class GetRestaurantsUseCase(private val restaurantRepo: RestaurantRepository) {
    suspend fun invoke(): List<Restaurant> {
        return restaurantRepo.getRestaurants()
    }
}