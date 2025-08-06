import org.betaapps.foodie.data.model.Order

interface OrderRepository {
    suspend fun placeOrder(order: Order): Result<Unit>
    suspend fun getUserOrders(userId: String): List<Order>
}
