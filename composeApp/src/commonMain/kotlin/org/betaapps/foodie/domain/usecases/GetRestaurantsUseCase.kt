import kotlinx.coroutines.flow.Flow
import org.betaapps.foodie.data.model.Restaurant
import org.betaapps.foodie.domain.model.Response

class GetRestaurantsUseCase(private val restaurantRepo: RestaurantRepository) {
    suspend fun invoke(): Flow<Response<List<Restaurant>>> {
        return restaurantRepo.getRestaurants()
    }
}