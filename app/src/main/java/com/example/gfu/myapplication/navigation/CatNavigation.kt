package com.example.gfu.myapplication.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gfu.myapplication.home.ui.HomeScreen

@Composable
fun CatNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = "home",
        builder = {
            composable(route = "home") {
                HomeScreen()
            }


            composable("greet/{name}") {
                val name: String = it.arguments?.getString("name") ?: "Unknown"
                Text("Hello $name")
            }
        }
    )
}