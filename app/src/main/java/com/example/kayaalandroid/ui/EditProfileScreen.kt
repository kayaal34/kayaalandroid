package com.example.kayaalandroid.ui
import android.content.Context
import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.kayaalandroid.viewmodels.ProfileViewModel
import androidx.core.content.ContextCompat
import android.content.ContentValues
import android.provider.MediaStore
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarResult


private fun createImageUri(context: Context): Uri {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "avatar_${System.currentTimeMillis()}.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }
    return context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        ?: throw IllegalStateException("Не удалось создать URI изображения")
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    onSaveClick: () -> Unit,
    profileViewModel: ProfileViewModel = viewModel()
) {
    val nameState = profileViewModel.name.collectAsState()
    val resumeUrlState = profileViewModel.resumeUrl.collectAsState()
    val avatarUriState = profileViewModel.avatarUri.collectAsState()

    var name by rememberSaveable { mutableStateOf("") }
    var resumeUrl by rememberSaveable { mutableStateOf("") }
    var avatarUri by rememberSaveable { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current
    var cameraImageUri by remember { mutableStateOf<Uri?>(null) }

    LaunchedEffect(nameState.value, resumeUrlState.value, avatarUriState.value) {
        name = nameState.value
        resumeUrl = resumeUrlState.value
        avatarUri = avatarUriState.value
    }

    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { avatarUri = it.toString() }
    }

    val takePictureLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            cameraImageUri?.let { avatarUri = it.toString() }
        }
    }

    val requestStoragePermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) showDialog = true
    }
    val snackbarHostState = remember { SnackbarHostState() }

    val requestCameraPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            val uri = createImageUri(context)
            cameraImageUri = uri
            takePictureLauncher.launch(uri)
        }
    }
    val jobTitleState = profileViewModel.jobTitle.collectAsState()
    var jobTitle by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(jobTitleState.value) {
        jobTitle = jobTitleState.value
    }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Выберите источник изображения") },
            text = {
                Column {
                    Text(
                        "Галерея",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showDialog = false
                                pickImageLauncher.launch("image/*")
                            }
                            .padding(8.dp)
                    )
                    Text(
                        "Камера",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showDialog = false
                                requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                            .padding(8.dp)
                    )
                }
            },
            confirmButton = {},
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Отмена")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Редактирование") },
                actions = {
                    IconButton(onClick = {
                        profileViewModel.saveProfile(name, resumeUrl, avatarUri, jobTitle)

                        onSaveClick()
                    }) {
                        Icon(Icons.Default.Check, contentDescription = "Сохранить")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val avatarClick = {
                showDialog = true
            }

            if (avatarUri.isNotBlank()) {
                Image(
                    painter = rememberAsyncImagePainter(avatarUri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .clickable { avatarClick() }
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Аватар",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .clickable { avatarClick() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("фио ") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = resumeUrl,
                onValueChange = { resumeUrl = it },
                label = { Text("Ссылка на резюме") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = jobTitle,
                onValueChange = { jobTitle = it },
                label = { Text("Профессия") },
                modifier = Modifier.fillMaxWidth()
            )
            if (avatarUri.isNotBlank()) {
                TextButton(
                    onClick = { avatarUri = "" },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Удалить фото", color = Color.Red)
                }
            }

        }
    }
}
