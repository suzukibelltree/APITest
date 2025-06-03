package com.example.apitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.apitest.RickandMortyAPI.CharactersScreen
import com.example.apitest.RickandMortyAPI.CharactersViewModel
import com.example.apitest.RickandMortyAPI.Retrofit
import com.example.apitest.ui.theme.APITestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APITestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewmodel = CharactersViewModel(
                        apiService = Retrofit.api
                    )
                    CharactersScreen(
                        viewModel = viewmodel
                    )
                }
            }
        }
    }
}
