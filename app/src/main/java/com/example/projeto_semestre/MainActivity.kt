package com.example.projeto_semestre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import data.AppDatabase
import data.ReservaRepository
import ui.MainScreen
import viewmodel.ReservaViewModel
import viewmodel.ReservaViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = ReservaRepository(database.reservaDao())
        val viewModelFactory = ReservaViewModelFactory(repository)
        val viewModel: ReservaViewModel by viewModels { viewModelFactory }

        setContent {
            MaterialTheme {
                Surface {
                    MainScreen(
                        viewModel = viewModel,
                        onSalvar = { reserva ->
                            viewModel.adicionarReserva(reserva)
                        }
                    )
                }
            }
        }
    }
}


