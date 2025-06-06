package viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import data.AppDatabase
import data.ReservaRepository

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val application = this[APPLICATION_KEY] as Application
            val database = AppDatabase.getDatabase(application.applicationContext)
            val repository = ReservaRepository(database.reservaDao())
            ReservaViewModel(repository)
        }
    }
}

