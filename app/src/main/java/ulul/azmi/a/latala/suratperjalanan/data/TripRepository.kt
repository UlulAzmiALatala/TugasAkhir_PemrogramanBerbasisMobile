// file TripRepository.kt

package ulul.azmi.a.latala.suratperjalanan.data

class TripRepository(private val dataSource: TripDataSource) {

    suspend fun getTrips(): List<TripData> {
        return dataSource.getTrips()
    }

    suspend fun getTripById(id: Int): TripData? {
        return dataSource.getTripById(id)
    }

    suspend fun addTrip(trip: TripData) {
        dataSource.insertTrip(trip)
    }

    suspend fun editTrip(trip: TripData) {
        dataSource.updateTrip(trip)
    }

    suspend fun deleteTrip(id: Int) {
        dataSource.deleteTrip(id)
    }
}


