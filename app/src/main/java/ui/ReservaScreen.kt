package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.Reserva

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservaScreen(reservas: List<Reserva>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(reservas) { reserva ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Cliente: ${reserva.nomeCliente}")
                    Text(text = "Data: ${reserva.data}")
                    Text(text = "Horário: ${reserva.horario}")
                    Text(text = "Campo: ${reserva.campo}")
                    Text(text = "Valor: R$ ${reserva.valor}")
                    Text(text = "Pagamento: ${reserva.formaPagamento}")
                }
            }
        }
    }
}