plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt") // ✅ Eğer kapt kullanacaksan bu satır
}

android {
    namespace = "com.example.kayaalandroid"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.kayaalandroid"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}
android {
    buildFeatures {
        compose = true // 📌 Compose'u etkinleştir
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0" // 📌 Kotlin Compose sürümünü doğrula
    }
}

dependencies {

        // Compose BOM
        implementation(platform("androidx.compose:compose-bom:2023.06.01"))

        // Material3 (ExposedDropdownMenu dahil)
        implementation("androidx.compose.material3:material3")

        // Compose Temel UI
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
        implementation("androidx.compose.foundation:foundation:1.5.0")
        implementation("androidx.compose.material:material:1.5.0")
        implementation("androidx.compose.material:material-icons-extended:1.5.0")

        // Compose Activity ve ViewModel
        implementation("androidx.activity:activity-compose:1.8.0")
        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")

        // Navigation
        implementation("androidx.navigation:navigation-compose:2.6.0")
        implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
        implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

        // Retrofit ve JSON
        implementation("com.squareup.retrofit2:retrofit:2.9.0")
        implementation("com.squareup.retrofit2:converter-gson:2.9.0")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

        // Core ve Gerekli Kütüphaneler
        implementation("androidx.datastore:datastore-preferences:1.0.0")
        implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
        implementation("com.google.android.material:material:1.6.1")
        implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.material3:material3:1.0.1")
        // Coil (Resim yükleme)
        implementation("io.coil-kt:coil-compose:2.3.0")
// Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1") // ✅ kapt kullanıyorsan bu

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
