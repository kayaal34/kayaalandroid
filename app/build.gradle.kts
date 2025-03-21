plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
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
    // ✅ Retrofit ve JSON Dönüştürücü
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // ✅ ViewModel, LiveData, Lifecycle ve Coroutine Desteği
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0") // 📌 Eksik olan ViewModel Compose
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4") // 📌 Eksik olan Coroutine Desteği

    implementation("androidx.compose.ui:ui:1.5.0") // Compose UI Kütüphanesi
    implementation("androidx.compose.foundation:foundation:1.5.0") // 📌 Eksikse ekleyin!

    implementation("androidx.compose.material:material:1.5.0")
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.material:material:1.5.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("io.coil-kt:coil-compose:2.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0") // ✅ Serialization için
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // ✅ Retrofit için JSON desteği
    // Material Icons (for bottom navigation icons)
    implementation("androidx.compose.material:material-icons-extended:1.5.0")
    // ✅ Material, Compose ve UI Bileşenleri
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.compose.material:material:1.5.0")
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.ui:ui:1.5.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.0")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.navigation:navigation-compose:2.6.0")

    implementation("androidx.navigation:navigation-compose:2.6.0") // 📌 Navigasyon Desteği
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0") // 📌 Lifecycle Destek


    // ✅ Compose ve ViewModel Entegrasyonu
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0") // 📌 Eksik olan ViewModel desteği
    implementation("androidx.activity:activity-compose:1.7.0") // 📌 Eksik olan Activity-Compose desteği
    implementation( "org.jetbrains.kotlin:kotlin-stdlib:1.9.0") // // Güncel versiyon olabilir
    // ✅ Resim Yükleme İçin Coil
    implementation("io.coil-kt:coil-compose:2.3.0")

    // ✅ Material Icons (Genişletilmiş)
    implementation("androidx.compose.material:material-icons-extended:1.5.0")

    // ✅ Core Kütüphaneleri
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // ✅ Test Bağımlılıkları
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
