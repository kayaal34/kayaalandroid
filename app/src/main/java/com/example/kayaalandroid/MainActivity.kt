package com.example.kayaalandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import com.example.kayaalandroid.ui.NavGraph
import com.example.kayaalandroid.ui.theme.KayaalandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KayaalandroidTheme {
                val navController = rememberNavController()

                Scaffold {
                    NavGraph(navController = navController) // ✅ movies parametresi kaldırıldı
                }
            }
        }
    }
}
