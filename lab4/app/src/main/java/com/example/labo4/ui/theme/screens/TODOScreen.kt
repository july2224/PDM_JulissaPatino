package com.example.labo4.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.labo4.data.model.Task
import com.example.labo4.components.TaskCard
import com.example.labo4.viewmodel.GeneralViewModel
import java.util.Date

data class CustomCardData(
    val pos: Int,
    val title: String,
    val description: String,
    val endDate: Date,
    val checked: Boolean
)

@Composable
fun TODOScreen(viewModel: GeneralViewModel, modifier: Modifier = Modifier) {
    val tasks = viewModel.tasks.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val newCard = remember { mutableStateOf(CustomCardData(1, "",
        "", Date(), false)) }
    var tempTitle by remember { mutableStateOf("") }
    var tempDesc by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Añadir tarea")
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "Mi lista de tareas", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            val lista = remember { mutableStateListOf<CustomCardData>() }
            lista.clear()

            tasks.value.forEach { task ->
                Log.d("Task", task.toString())
                lista.add(
                    CustomCardData(
                        pos = task.id,
                        title = task.title,
                        description = task.description,
                        endDate = task.endDate,
                        checked = task.isCompleted
                    )
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tasks.value) { task ->
                    TaskCard(task = task)
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Añadir nueva tarea") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = tempTitle,
                            onValueChange = { tempTitle = it },
                            label = { Text("titulo") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = tempDesc,
                            onValueChange = { tempDesc = it },
                            label = { Text("Descripcion?") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (tempTitle.isNotBlank()) {
                                newCard.value = CustomCardData(
                                    pos = tasks.value.size + 1,
                                    title = tempTitle,
                                    description = tempDesc,
                                    endDate = Date(),
                                    checked = false
                                )

                                val task = Task(
                                    id = newCard.value.pos,
                                    title = newCard.value.title,
                                    description = newCard.value.description,
                                    endDate = newCard.value.endDate,
                                    isCompleted = newCard.value.checked
                                )
                                viewModel.addTask(task)
                                tempTitle = ""
                                tempDesc = ""
                                showDialog = false
                            }
                        }
                    ) {
                        Text("Añadir")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}