package com.example.jupfuno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaLaboratorio()
        }
    }
}

@Composable
fun PantallaLaboratorio() {
    var nombre by remember { mutableStateOf("") }
    val listaNombres = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9FE))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // para escribir nombre
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF3E5F5),
                unfocusedContainerColor = Color(0xFFF3E5F5),
                focusedIndicatorColor = Color(0xFFCE93D8),
                unfocusedIndicatorColor = Color(0xFFE1BEE7)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // boton guardar
        Button(
            onClick = {
                if (nombre.isNotBlank()) {
                    listaNombres.add(nombre)
                    nombre = ""
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB2DFDB)
            )
        ) {
            Text("Guardar", color = Color(0xFF004D40))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // titulo y boton limpiar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "nombres y posiciones", fontSize = 14.sp, color = Color(0xFF7B1FA2))

            Button(
                onClick = { listaNombres.clear() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFCCBC)
                )
            ) {
                Text("Limpiar", fontSize = 12.sp, color = Color(0xFFBF360C))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // cuadro grande y lista
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .border(3.dp, Color(0xFFBBDEFB), RoundedCornerShape(12.dp)) // borde azul cielo pastel
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            LazyColumn {
                itemsIndexed(listaNombres) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = item, color = Color(0xFF455A64)) // nombre
                        Text(
                            text = "${index + 1}",
                            color = Color(0xFF90CAF9),
                            fontSize = 16.sp
                        ) // posicion
                    }
                }
            }
        }
    }
}