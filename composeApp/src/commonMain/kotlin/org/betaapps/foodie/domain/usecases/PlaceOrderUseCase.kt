import org.betaapps.foodie.data.model.Order

class PlaceOrderUseCase(private val orderRepo: OrderRepository) {
    suspend fun invoke(order: Order): Result<Unit> {
        return orderRepo.placeOrder(order)
    }
}