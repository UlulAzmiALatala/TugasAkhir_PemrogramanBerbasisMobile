// file MainActivity.kt

package ulul.azmi.a.latala.suratperjalanan

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import ulul.azmi.a.latala.suratperjalanan.data.TripRepository
import ulul.azmi.a.latala.suratperjalanan.data.TripDataSourceImpl
import ulul.azmi.a.latala.suratperjalanan.ui.screens.*
import ulul.azmi.a.latala.suratperjalanan.ui.theme.SuratPerjalananTheme
import ulul.azmi.a.latala.suratperjalanan.ui.viewmodel.TripViewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ulul.azmi.a.latala.suratperjalanan.viewmodel.TripViewModel
import ulul.azmi.a.latala.suratperjalanan.data.TripData

class MainActivity : ComponentActivity() {
    private lateinit var tripViewModel: TripViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuratPerjalananTheme {
                val navController = rememberNavController()

                // Inisialisasi TripDataSource
                val dataSource = TripDataSourceImpl()
                // Inisialisasi TripRepository
                val repository = TripRepository(dataSource)

                // Inisialisasi ViewModel dengan factory
                val factory = TripViewModelFactory(repository)
                tripViewModel = ViewModelProvider(this, factory).get(TripViewModel::class.java)

                NavHost(
                    navController = navController,
                    startDestination = "splash" // Set splash sebagai start destination
                ) {

                    composable("splash") {
                        SplashScreen(onTimeout = {
                            navController.navigate("tripList") {
                                popUpTo("splash") { inclusive = true } // Hapus splash dari back stack
                            }
                        })
                    }

                    composable("tripList") {
                        TripListScreen(
                            trips = tripViewModel.trips,
                            onAddTripClick = {
                                navController.navigate("addTrip")
                            },
                            onDeleteTrip = { id -> tripViewModel.onDeleteTrip(id) },
                            onEditTrip = { trip -> navController.navigate("editTrip/${trip.id}") },
                            onDetailTrip = { id -> navController.navigate("detail/$id") },
                            navController = navController
                        )
                    }

                    composable("editTrip/{id}") { backStackEntry ->
                        val tripId = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
                        val trip = tripViewModel.trips.find { it.id == tripId }
                        if (trip != null) {
                            TripEditScreen(
                                trip = trip,
                                onSaveEdit = { updatedTrip -> tripViewModel.onEditTrip(updatedTrip) },
                                navController = navController
                            )
                        }
                    }

                    composable("addTrip") {
                        AddTripScreen(
                            onSaveTrip = { nama, tujuan, tanggal, keterangan ->
                                // Logika untuk menyimpan perjalanan baru
                                val newTrip = TripData(
                                    id = tripViewModel.trips.size + 1, // Ganti dengan logika ID yang sesuai
                                    nama = nama,
                                    tujuan = tujuan,
                                    tanggal = tanggal,
                                    keterangan = keterangan
                                )
                                tripViewModel.onAddTrip(newTrip) // Panggil fungsi untuk menambah trip
                                navController.popBackStack() // Kembali ke tripList setelah menambah
                            },
                            onCancel = {
                                navController.popBackStack() // Kembali ke tripList jika dibatalkan
                            }
                        )
                    }
                    composable("detail/{tripId}") { backStackEntry ->
                        val tripId = backStackEntry.arguments?.getString("tripId")?.toInt() ?: 0
                        val trip = tripViewModel.trips.find { it.id == tripId }
                        if (trip != null) {
                            TripDetailScreen(
                                trip = trip,
                                onBackClick = { navController.popBackStack() } // Menyediakan fungsi untuk kembali
                            )
                        }
                    }
                }
            }
        }
    }
}

