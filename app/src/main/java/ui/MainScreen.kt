package ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.Reserva
import viewmodel.ReservaViewModel

@Composable
fun MainScreen(
    viewModel: ReservaViewModel,

    onSalvar: (Reserva) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Nova Reserva", style = MaterialTheme.typography.titleLarge)

        FormularioReserva(
            reservaViewModel = viewModel
        )

        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        ListaReservas(
            viewModel = viewModel,
            onEditar = { reserva ->
                Log.d("MainScreen", "Tentativa de editar reserva: $reserva")
                viewModel.iniciarEdicao(reserva)
            }
        )
    }
}


