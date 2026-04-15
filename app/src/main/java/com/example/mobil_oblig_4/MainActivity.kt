package com.example.mobil_oblig_4

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mobil_oblig_4.network.ApiService
import com.example.mobil_oblig_4.network.RetrofitInstance
import androidx.lifecycle.lifecycleScope
import com.example.compose.AppTheme
import com.example.mobil_oblig_4.ui.MyBikeApp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            try {
                val brands = RetrofitInstance.api.getBrands()
                Log.d("API_TEST", brands.toString())
            } catch (e: Exception) {
                Log.e("API_TEST", "Error: ${e.message}")
            }
        }

        setContent {
            AppTheme {
                MyBikeApp()
            }
        }
    }
}

