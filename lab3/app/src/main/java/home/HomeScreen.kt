package com.example.labo3.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate("nombres") }) {
            Text("Ver lista de nombres")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("sensor") }) {
            Text("Ver giroscopio")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    HomeScreen(navController)
}