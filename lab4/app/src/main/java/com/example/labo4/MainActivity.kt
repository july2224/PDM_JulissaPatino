package com.example.labo4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.labo4.screens.TODOScreen
import com.example.labo4.ui.theme.Labo4Theme
import com.example.labo4.viewmodel.GeneralViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labo4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    val generalViewModel: GeneralViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = "todo_screen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = "todo_screen") {
                            TODOScreen(viewModel = generalViewModel)
                        }

                    }
                }
            }
        }
    }
}