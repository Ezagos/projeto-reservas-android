package data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservaDao {

    @Query("SELECT * FROM reservas ORDER BY data, horario")
    fun getTodasReservas(): Flow<List<Reserva>>

    @Insert
    suspend fun inserirReserva(reserva: Reserva)

    @Update
    suspend fun atualizarReserva(reserva: Reserva)

    @Delete
    suspend fun deletarReserva(reserva: Reserva)

    @Query("SELECT * FROM reservas WHERE id = :id")
    suspend fun getReservaPorId(id: Int): Reserva?
}