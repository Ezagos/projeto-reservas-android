package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.Reserva
import viewmodel.ReservaViewModel

@Composable
fun ListaReservas(
    viewModel: ReservaViewModel,
    onEditar: (Reserva) -> Unit,
    modifier: Modifier = Modifier
) {
    val reservas by viewModel.reservas.collectAsState()

    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(reservas) { reserva ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Cliente: ${reserva.nomeCliente}")
                    Text("Campo: ${reserva.campo}")
                    Text("Data: ${reserva.data}")
                    Text("Horário: ${reserva.horario}")
                    Text("Valor: R$ ${reserva.valor}")
                    Text("Pagamento: ${reserva.formaPagamento}")

                    Spacer(modifier = Modifier.height(8.dp))

                    Row {
                        Button(
                            onClick = { onEditar(reserva) },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Editar")
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = { viewModel.deletarReserva(reserva) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }
    }
}
