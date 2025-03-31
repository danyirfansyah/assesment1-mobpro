package com.dany0067.kalkulator.navigation

sealed class Screen (val route: String) {
    data object Home : Screen("mainScreen")
}