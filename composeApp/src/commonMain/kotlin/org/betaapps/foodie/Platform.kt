package org.betaapps.foodie

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform