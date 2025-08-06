package org.betaapps.foodie.data.repository

import org.betaapps.foodie.data.model.Dish
import org.betaapps.foodie.data.model.Restaurant
import RestaurantRepository

class RestaurantRepositoryImpl : RestaurantRepository {
    override suspend fun getRestaurants(): List<Restaurant> = listOf(
        Restaurant(
            id ="1",
            name ="Pizza Place",
           imageUrl = "https://firebasestorage.googleapis.com/v0/b/rentyourcar-446809.firebasestorage.app/o/Screenshot%202025-06-29%20at%2012.40.16.png?alt=media&token=3034ac32-f692-46f2-bc02-001bc068e9c3",
            dishes =  listOf(
                Dish("1", "Pepperoni", description = "pepperoni pepperoni","12.99", "https://firebasestorage.googleapis.com/v0/b/rentyourcar-446809.firebasestorage.app/o/Screenshot%202025-06-29%20at%2012.40.16.png?alt=media&token=3034ac32-f692-46f2-bc02-001bc068e9c3" , ),
                Dish("2", "Margherita", description = "Margherita Margherita","10.99", "https://firebasestorage.googleapis.com/v0/b/rentyourcar-446809.firebasestorage.app/o/Screenshot%202025-06-29%20at%2012.40.16.png?alt=media&token=3034ac32-f692-46f2-bc02-001bc068e9c3")
            ),
            rating = "4.5",
            deliveryTime = "20",
            address = "",
            phoneNumber = ""

        ),
        Restaurant(
            id = "2",
            name = "Burger House", imageUrl ="https://firebasestorage.googleapis.com/v0/b/rentyourcar-446809.firebasestorage.app/o/Screenshot%202025-06-29%20at%2012.40.07.png?alt=media&token=5a9342dd-3776-44ad-b4a5-0741e506bfac",
            dishes = listOf(
            Dish("3", "Classic Burger", description = "classic burger", "8.99", "",)
        ),
            rating = "4.5",
            deliveryTime = "20",
            address = "",
            phoneNumber = ""
        )
    )

    override suspend fun getRestaurantDetails(id: String): Restaurant = getRestaurants().first()
}