package org.betaapps.foodie.di

import GetRestaurantsUseCase
import RestaurantRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.betaapps.foodie.data.repository.AuthRepositoryImpl
import org.betaapps.foodie.data.repository.ProfileRepositoryImpl
import org.betaapps.foodie.data.repository.RestaurantRepositoryImpl
import org.betaapps.foodie.domain.repository.AuthRepository
import org.betaapps.foodie.domain.repository.ProfileRepository
import org.betaapps.foodie.domain.usecases.ForgotPasswordUseCase
import org.betaapps.foodie.domain.usecases.GetUserUseCase
import org.betaapps.foodie.domain.usecases.LoginUseCase
import org.betaapps.foodie.domain.usecases.RegisterUserUseCase
import org.betaapps.foodie.presentation.login.LoginViewModel
import org.betaapps.foodie.presentation.profile.ProfileScreenViewModel
import org.betaapps.foodie.presentation.register.RegisterScreenViewModel
import org.betaapps.foodie.presentation.restaurants.restaurantslist.RestaurantsListScreenViewModel
import org.betaapps.foodie.presentation.shoppingcart.ShoppingCartViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<RestaurantRepository> { RestaurantRepositoryImpl(get()) }
    single<ProfileRepository> { ProfileRepositoryImpl(get()) }
    single { LoginUseCase(get()) }
    single { GetRestaurantsUseCase(get()) }
    single { RegisterUserUseCase(get()) }
    single { ForgotPasswordUseCase(get()) }
    single { GetUserUseCase(get() , get()) }

    single { ShoppingCartViewModel() }
    single { RestaurantsListScreenViewModel(get()) }
    single { LoginViewModel(get() , get()) }
    single { RegisterScreenViewModel(get()) }
    single { ProfileScreenViewModel(get()) }
}

fun initKoin() {
    startKoin { modules(appModule) }
}