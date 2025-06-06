package data

import kotlinx.coroutines.flow.Flow

class ReservaRepository(private val reservaDao: ReservaDao) {

    val todasAsReservas: Flow<List<Reserva>> = reservaDao.getTodasReservas()

    suspend fun inserir(reserva: Reserva) {
        reservaDao.inserirReserva(reserva)
    }

    suspend fun atualizar(reserva: Reserva) {
        reservaDao.atualizarReserva(reserva)
    }

    suspend fun deletar(reserva: Reserva) {
        reservaDao.deletarReserva(reserva)
    }


}