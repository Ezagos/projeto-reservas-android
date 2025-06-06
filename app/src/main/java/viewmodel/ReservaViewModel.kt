package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.Reserva
import data.ReservaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ReservaViewModel(private val repository: ReservaRepository) : ViewModel() {

    private val _reservas = MutableStateFlow<List<Reserva>>(emptyList())
    val reservas: StateFlow<List<Reserva>> = _reservas.asStateFlow()

    private val _reservaEmEdicao = MutableStateFlow<Reserva?>(null)
    val reservaEmEdicao: StateFlow<Reserva?> = _reservaEmEdicao.asStateFlow()

    fun iniciarEdicao(reserva: Reserva) {
        _reservaEmEdicao.value = reserva
    }

    fun cancelarEdicao() {
        _reservaEmEdicao.value = null
    }


    init {
        viewModelScope.launch {
            repository.todasAsReservas.collect {
                _reservas.value = it
            }
        }
    }

    fun adicionarReserva(reserva: Reserva) {
        viewModelScope.launch {
            repository.inserir(reserva)
        }
    }

    fun atualizarReserva(reserva: Reserva) {
        viewModelScope.launch {
            repository.atualizar(reserva)
        }
    }

    fun deletarReserva(reserva: Reserva) {
        viewModelScope.launch {
            repository.deletar(reserva)
        }
    }
}