// file TripDataSourceImpl.kt

package ulul.azmi.a.latala.suratperjalanan.data

class TripDataSourceImpl : TripDataSource {
	private val trips = mutableListOf<TripData>() // Simpan data perjalanan di sini

	override suspend fun getTrips(): List<TripData> {
		return trips // Kembalikan daftar perjalanan
	}

	override suspend fun getTripById(id: Int): TripData? {
		return trips.find { it.id == id } // Kembalikan perjalanan berdasarkan ID
	}

	override suspend fun insertTrip(trip: TripData) {
		trips.add(trip) // Tambahkan perjalanan ke daftar
	}

	override suspend fun updateTrip(trip: TripData) {
		val index = trips.indexOfFirst { it.id == trip.id }
		if (index != -1) {
			trips[index] = trip // Perbarui perjalanan
		}
	}

	override suspend fun deleteTrip(id: Int) {
		trips.removeIf { it.id == id } // Hapus perjalanan berdasarkan ID
	}
}



