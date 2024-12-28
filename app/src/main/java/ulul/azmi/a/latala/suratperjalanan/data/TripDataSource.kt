// file TripDataSourse.kt

package ulul.azmi.a.latala.suratperjalanan.data

interface TripDataSource {
    suspend fun getTrips(): List<TripData>
    suspend fun getTripById(id: Int): TripData?
    suspend fun insertTrip(trip: TripData)
    suspend fun updateTrip(trip: TripData)
    suspend fun deleteTrip(id: Int)
}


