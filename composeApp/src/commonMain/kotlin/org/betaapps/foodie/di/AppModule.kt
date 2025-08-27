package org.betaapps.foodie.di

import org.betaapps.foodie.domain.repository.AuthRepository
import GetRestaurantsUseCase
import org.betaapps.foodie.domain.usecases.LoginUseCase
import RestaurantRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.betaapps.foodie.data.repository.AuthRepositoryImpl
import org.betaapps.foodie.data.repository.RestaurantRepositoryImpl
import org.betaapps.foodie.presentation.login.LoginViewModel
import org.betaapps.foodie.presentation.restaurants.restaurantslist.RestaurantsListScreenViewModel
import org.betaapps.foodie.presentation.shoppingcart.ShoppingCartViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }

    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<RestaurantRepository> { RestaurantRepositoryImpl(get()) }

    single { LoginUseCase(get()) }
    single { GetRestaurantsUseCase(get()) }

    single { ShoppingCartViewModel() }
    single { RestaurantsListScreenViewModel(get()) }
    single {LoginViewModel(get())}
}

fun initKoin(){
    startKoin { modules(appModule) }
}