package data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservas")

data class Reserva(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nomeCliente: String,
    val data: String,
    val horario: String,
    val campo: String,
    val valor: String,
    val formaPagamento: String
)

