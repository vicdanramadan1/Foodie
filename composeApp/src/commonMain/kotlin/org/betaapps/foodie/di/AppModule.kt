package org.betaapps.foodie.di

import AuthRepository
import GetRestaurantsUseCase
import LoginUseCase
import RestaurantRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.betaapps.foodie.data.repository.AuthRepositoryImpl
import org.betaapps.foodie.data.repository.RestaurantRepositoryImpl
import org.betaapps.foodie.presentation.restaurants.restaurantslist.RestaurantsListScreenViewModel
import org.betaapps.foodie.presentation.shoppingcart.ShoppingCartViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }

    single { ShoppingCartViewModel() }
    single<AuthRepository> { AuthRepositoryImpl() }
    single<RestaurantRepository> { RestaurantRepositoryImpl(get()) }
    single { LoginUseCase(get()) }
    single { GetRestaurantsUseCase(get()) }

    single { RestaurantsListScreenViewModel(get()) }
}

fun initKoin(){
    startKoin { modules(appModule) }
}