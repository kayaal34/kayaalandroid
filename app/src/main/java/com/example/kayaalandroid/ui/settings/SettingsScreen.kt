package com.example.kayaalandroid.ui.settings

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onSaveSuccess: () -> Unit = {},
    genreOptions: List<String>
) {
    val context = LocalContext.current
    val preferences = remember { FilterPreferences(context) }
    val scope = rememberCoroutineScope()

    val currentFilters by preferences.filtersFlow.collectAsState(initial = FilterState("", 0, ""))

    var genre by remember { mutableStateOf(currentFilters.genre) }
    var expanded by remember { mutableStateOf(false) }
    var minRating by remember { mutableStateOf(currentFilters.minRating.toFloat()) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Настройки") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Genre Dropdown
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = genre,
                    onValueChange = { genre = it },
                    readOnly = true,
                    label = { Text("Жанр фильма") },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    genreOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                genre = option
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Slider
            Text("Минимальная оценка: ${minRating.toInt()}")
            Slider(
                value = minRating,
                onValueChange = { minRating = it },
                valueRange = 0f..10f,
                steps = 9,
                modifier = Modifier.fillMaxWidth()
            )

            // Kaydet ve Temizle Butonları
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                TextButton(
                    onClick = {
                        genre = ""
                        minRating = 0f
                        scope.launch {
                            preferences.saveFilters("", 0, "")
                            onSaveSuccess()
                        }
                    }
                ) {
                    Text("Сбросить")
                }

                Button(
                    onClick = {
                        scope.launch {
                            preferences.saveFilters(genre, minRating.toInt(), "")
                            onSaveSuccess()
                        }
                    }
                ) {
                    Text("Сохранить")
                }
            }
        }
    }
}