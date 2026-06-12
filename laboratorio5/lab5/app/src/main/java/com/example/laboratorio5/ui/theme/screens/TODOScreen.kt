package com.example.laboratorio5.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.laboratorio5.InitDatabase
import com.example.laboratorio5.Post
import kotlinx.coroutines.launch

@Composable
fun TODOScreen(modifier: Modifier = Modifier) {
    val postDao = InitDatabase.database.postDao()
    val posts by postDao.getAllPosts().collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()
    
    var showDialog by remember { mutableStateOf(false) }
    var tempTitle by remember { mutableStateOf("") }
    var tempContent by remember { mutableStateOf("") }

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

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(posts) { post ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = post.title, style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = post.content, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
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
                            value = tempContent,
                            onValueChange = { tempContent = it },
                            label = { Text("Descripcion") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },

                confirmButton = {
                    Button(
                        onClick = {
                            if (tempTitle.isNotBlank()) {
                                coroutineScope.launch {
                                    val newPost = Post(
                                        id = (posts.maxOfOrNull { it.id } ?: 0) + 1,
                                        title = tempTitle,
                                        content = tempContent
                                    )
                                    postDao.insertPost(newPost)
                                    tempTitle = ""
                                    tempContent = ""
                                    showDialog = false
                                }
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
