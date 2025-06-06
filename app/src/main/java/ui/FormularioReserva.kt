package ui

import android.widget.Toast
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import data.Reserva
import viewmodel.ReservaViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioReserva(
    reservaViewModel: ReservaViewModel
) {
    val reservaEmEdicao by reservaViewModel.reservaEmEdicao.collectAsState()
    val context = LocalContext.current


    var nomeCliente by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var horario by remember { mutableStateOf("") }
    var valor by remember { mutableStateOf("") }
    var campoSelecionado by remember { mutableStateOf("") }
    var formaPagamentoSelecionada by remember { mutableStateOf("") }


    var campoExpanded by remember { mutableStateOf(false) }
    val campoOptions = listOf("1", "2", "3", "4", "5")

    var formaPagamentoExpanded by remember { mutableStateOf(false) }
    val formaPagamentoOptions = listOf("Dinheiro", "Pix")

    LaunchedEffect(reservaEmEdicao) {
        reservaEmEdicao?.let {
            nomeCliente = it.nomeCliente
            data = it.data
            horario = it.horario
            valor = it.valor
            campoSelecionado = it.campo
            formaPagamentoSelecionada = it.formaPagamento
        } ?: run {

            nomeCliente = ""
            data = ""
            horario = ""
            valor = ""
            campoSelecionado = ""
            formaPagamentoSelecionada = ""
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = nomeCliente,
            onValueChange = { nomeCliente = it },
            label = { Text("Nome do Jogador") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            data = "${dayOfMonth.toString().padStart(2, '0')}/${(month + 1).toString().padStart(2, '0')}/$year"
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
        ) {
            OutlinedTextField(
                value = data,
                onValueChange = { },
                label = { Text("Data") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                enabled = false,
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                )

            )
        }
        Spacer(modifier = Modifier.height(8.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val calendar = Calendar.getInstance()
                    TimePickerDialog(
                        context,
                        { _, selectedHour, selectedMinute ->
                            horario = "${selectedHour.toString().padStart(2, '0')}:${selectedMinute.toString().padStart(2, '0')}"
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                }
        ) {
            OutlinedTextField(
                value = horario,
                onValueChange = { },
                label = { Text("Horário") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                enabled = false,
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = campoExpanded,
            onExpandedChange = { campoExpanded = !campoExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = campoSelecionado,
                onValueChange = { },
                label = { Text("Campo") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = campoExpanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = campoExpanded,
                onDismissRequest = { campoExpanded = false }
            ) {
                campoOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            campoSelecionado = option
                            campoExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = formaPagamentoExpanded,
            onExpandedChange = { formaPagamentoExpanded = !formaPagamentoExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = formaPagamentoSelecionada,
                onValueChange = { },
                label = { Text("Forma de Pagamento") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = formaPagamentoExpanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = formaPagamentoExpanded,
                onDismissRequest = { formaPagamentoExpanded = false }
            ) {
                formaPagamentoOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            formaPagamentoSelecionada = option
                            formaPagamentoExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = valor,
            onValueChange = { novoValor ->
                if (novoValor.all { char -> char.isDigit() || char == '.' } && novoValor.count { it == '.' } <= 1) {
                    valor = novoValor
                }
            },
            label = { Text("Valor (R$)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            prefix = { Text("R$ ") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = {
                    val camposVazios = mutableListOf<String>()
                    if (nomeCliente.isBlank()) camposVazios.add("Nome do Jogador")
                    if (data.isBlank()) camposVazios.add("Data")
                    if (horario.isBlank()) camposVazios.add("Horário")
                    if (campoSelecionado.isBlank()) camposVazios.add("Campo")
                    if (formaPagamentoSelecionada.isBlank()) camposVazios.add("Forma de Pagamento")
                    if (valor.isBlank()) {
                        camposVazios.add("Valor")
                    } else {
                        try {
                            valor.toDouble()
                        } catch (e: NumberFormatException) {
                            camposVazios.add("Valor (inválido)")
                        }
                    }

                    if (camposVazios.isNotEmpty()) {
                        val mensagem = "Falta preencher: ${camposVazios.joinToString(", ")}"
                        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show()
                    } else {
                        val valorFormatadoParaSalvar = valor
                        val reservaAtual = Reserva(
                            id = reservaEmEdicao?.id ?: 0,
                            nomeCliente = nomeCliente,
                            data = data,
                            horario = horario,
                            valor = valorFormatadoParaSalvar,
                            campo = campoSelecionado,
                            formaPagamento = formaPagamentoSelecionada
                        )

                        if (reservaEmEdicao != null) {
                            reservaViewModel.atualizarReserva(reservaAtual)
                        } else {
                            reservaViewModel.adicionarReserva(reservaAtual)
                            // Limpa os campos explicitamente após adicionar uma NOVA reserva
                            nomeCliente = ""
                            data = ""
                            horario = ""
                            valor = ""
                            campoSelecionado = ""
                            formaPagamentoSelecionada = ""
                        }
                        reservaViewModel.cancelarEdicao()
                    }
                }
            ) {
                Text(if (reservaEmEdicao != null) "Atualizar" else "Adicionar")
            }

            if (reservaEmEdicao != null) {
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(onClick = {
                    reservaViewModel.cancelarEdicao() // LaunchedEffect limpará os campos
                }) {
                    Text("Cancelar")
                }
            }
        }
    }
}
