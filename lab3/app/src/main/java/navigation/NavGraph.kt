package com.example.labo3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labo3.home.HomeScreen
import com.example.labo3.nombres.ListaNombresScreen
import com.example.labo3.sensor.LightSensor

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(navController)
        }

        composable("nombres") {
            ListaNombresScreen(navController)
        }

        composable("sensor") {
            LightSensor(navController)
        }
    }
}