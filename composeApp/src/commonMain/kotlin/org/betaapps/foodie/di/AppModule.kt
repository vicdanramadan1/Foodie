package org.betaapps.foodie.di

import AuthRepository
import GetRestaurantsUseCase
import LoginUseCase
import OrderRepository
import RestaurantRepository
import org.betaapps.foodie.data.repository.AuthRepositoryImpl
import org.betaapps.foodie.data.repository.RestaurantRepositoryImpl
import org.betaapps.foodie.presentation.shoppingcart.ShoppingCartViewModel
import org.koin.dsl.module

val appModule = module {
    single { ShoppingCartViewModel() }
    single<AuthRepository> { AuthRepositoryImpl() }
    single<RestaurantRepository> { RestaurantRepositoryImpl() }
    single { LoginUseCase(get()) }
    single { GetRestaurantsUseCase(get()) }
}