package com.example.kayaalandroid.ui

import androidx.compose.ui.graphics.Color
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.kayaalandroid.viewmodels.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onEditClick: () -> Unit = {},
    profileViewModel: ProfileViewModel = viewModel()
) {
    val name by profileViewModel.name.collectAsState()
    val resumeUrl by profileViewModel.resumeUrl.collectAsState()
    val avatarUri by profileViewModel.avatarUri.collectAsState()
    val jobTitle by profileViewModel.jobTitle.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Профиль") },
                actions = {
                    IconButton(onClick = onEditClick) {
                        Icon(Icons.Default.Edit, contentDescription = "Редактировать")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (avatarUri.isNotBlank()) {
                Image(
                    painter = rememberAsyncImagePainter(avatarUri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Avatar",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = name.ifBlank { "Имя не указано" }, fontSize = 22.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = if (jobTitle.isNotBlank()) jobTitle else "Профессия не указана",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    if (resumeUrl.isNotBlank()) {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(resumeUrl))
                        context.startActivity(intent)
                    }
                }
            ) {
                Text("Резюме")
            }
        }
    }
}
