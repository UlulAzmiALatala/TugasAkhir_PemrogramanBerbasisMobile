// file TripViewModel.kt

package ulul.azmi.a.latala.suratperjalanan.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ulul.azmi.a.latala.suratperjalanan.data.TripData
import ulul.azmi.a.latala.suratperjalanan.data.TripRepository

class TripViewModel(private val repository: TripRepository) : ViewModel() {
    var trips = mutableStateListOf<TripData>()
        private set

    init {
        getTrips()
    }

    private fun getTrips() {
        viewModelScope.launch {
            trips.clear() // Kosongkan daftar sebelum mengisi ulang
            trips.addAll(repository.getTrips()) // Ambil data perjalanan dari repository
        }
    }

    fun onAddTrip(trip: TripData) {
        viewModelScope.launch {
            repository.addTrip(trip) // Menyimpan perjalanan baru ke repository
            getTrips() // Memperbarui daftar perjalanan setelah menambah
        }
    }

    fun onDeleteTrip(id: Int) {
        viewModelScope.launch {
            repository.deleteTrip(id)
            getTrips() // Memperbarui daftar setelah penghapusan
        }
    }

    fun onEditTrip(updatedTrip: TripData) {
        viewModelScope.launch {
            repository.editTrip(updatedTrip)
            getTrips() // Memperbarui daftar setelah pengeditan
        }
    }
}
