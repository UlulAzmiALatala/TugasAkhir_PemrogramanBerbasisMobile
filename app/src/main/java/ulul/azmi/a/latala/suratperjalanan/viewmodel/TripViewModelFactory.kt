// file TripViewModelFactory.kt

package ulul.azmi.a.latala.suratperjalanan.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ulul.azmi.a.latala.suratperjalanan.data.TripRepository
import ulul.azmi.a.latala.suratperjalanan.viewmodel.TripViewModel

class TripViewModelFactory(private val repository: TripRepository) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(TripViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return TripViewModel(repository) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
