import kotlinx.coroutines.flow.Flow
import org.betaapps.foodie.data.model.Restaurant
import org.betaapps.foodie.domain.model.Response

interface RestaurantRepository {
    suspend fun getRestaurants(): Flow<Response<List<Restaurant>>>
  //  suspend fun getRestaurantDetails(id: String): Restaurant
}