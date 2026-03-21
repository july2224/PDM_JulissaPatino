package com.example.lab11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab11.ui.theme.LAB11Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LAB11Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Julissa",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    // EJERCICIO 1
    val compu = Computadora(8, 256, "Windows")
    val estado = compu.encender()

    val programas = listOf(
        "Notion 2026",
        "Facebook 2024",
        "Spotify 2026",
        "YouTube Music 2023"
    )

    val programas2026 = programas.filter { it.contains("2026") }

    // EJERCICIO 2
    val calc = Calculadora("Casio", 5, 20.0)
    val suma = calc.sumar(5.0, 10.0)
    val multiplica = calc.multiplicar(20.0, 2.0)
    val division = calc.dividir(3.0, 0.0)

    // EJERCICIO 3
    val ciclo01 = listOf(
        Estudiante("Maria","0001","Dispositivos Móviles"),
        Estudiante("Carol","0002","Dispositivos Móviles"),
        Estudiante("Karla","0003","Dispositivos Móviles"),

        Estudiante("Gerson","0004","Analisis numerico"),
        Estudiante("Ariel","0005","Analisis numerico"),
        Estudiante("Daphne","0006","Analisis numerico"),
        Estudiante("Simon","0007","Analisis numerico")
    )

    val moviles = ciclo01.filter {
        it.asignatura == "Dispositivos Móviles"
    }

    // UI (LO QUE FALTABA)
    Column(modifier = modifier) {

        Text("Estado: $estado")

        Text("Programas 2026:")
        for (p in programas2026) {
            Text(p)
        }

        Text("Suma: $suma")
        Text("Multiplicacion: $multiplica")
        Text("Division: $division")

        Text("Estudiantes de moviles:")
        for (e in moviles) {
            Text(e.nombre)
        }
    }
}

// CLASES

class Computadora(
    var ram: Int,
    var almacenamiento: Int,
    var sistemaOperativo: String
){
    fun encender() = "La computadora esta encendida"
    fun apagar() = "La computadora esta apagada"

    fun cambiarRAM(nueva: Int){
        ram = nueva
    }

    fun cambiarSisOP(nuevo: String){
        sistemaOperativo = nuevo
    }
}

class Calculadora(
    val marca: String,
    val aniosvida: Int,
    val precio: Double
){
    fun sumar(a: Double, b: Double) = a + b
    fun restar(a: Double, b: Double) = a - b
    fun multiplicar(a: Double, b: Double) = a * b

     fun dividir(a: Double, b: Double): String {
        return if (b == 0.0)
            "No se puede dividir entre 0"
        else
            (a / b).toString()
    }
}

data class Estudiante(
    val nombre: String,
    val carnet: String,
    val asignatura: String
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LAB11Theme {
        Greeting("Android")
    }
}