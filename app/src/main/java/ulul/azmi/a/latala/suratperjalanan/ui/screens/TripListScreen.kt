// file TripListScreen.kt

package ulul.azmi.a.latala.suratperjalanan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ulul.azmi.a.latala.suratperjalanan.R
import ulul.azmi.a.latala.suratperjalanan.data.TripData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripListScreen(
    trips: List<TripData>,
    onAddTripClick: () -> Unit,
    onDeleteTrip: (Int) -> Unit,
    onEditTrip: (TripData) -> Unit,
    onDetailTrip: (Int) -> Unit,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp) // Spasi antar elemen
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_kementerian), // Ganti dengan logo Anda
                            contentDescription = "Logo Kementerian",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp) // Spasi agar lebih rapi
                        )
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Daftar Perjalanan Dinas",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontSize = 20.sp, // Ukuran font yang lebih besar
                                    color = MaterialTheme.colorScheme.onPrimary // Menggunakan warna teks sesuai tema
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Kelola perjalanan dinas Anda",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 12.sp, // Ukuran font kecil untuk deskripsi tambahan
                                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f) // Warna lebih soft
                                )
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary // Menggunakan warna dari tema
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTripClick) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(trips.size) { index ->
                    val trip = trips[index]
                    TripItem(
                        trip = trip,
                        onDeleteTrip = onDeleteTrip,
                        onEditTrip = onEditTrip,
                        onDetailTrip = onDetailTrip,
                        fontSize = 17f,
                        navController = navController
                    )
                }
            }
        }
    )
}

@Composable
fun TripItem(
    trip: TripData,
    onDeleteTrip: (Int) -> Unit,
    onEditTrip: (TripData) -> Unit,
    onDetailTrip: (Int) -> Unit,
    fontSize: Float, // Menambahkan fontSize sebagai parameter
    navController: NavController // Menambahkan navController sebagai parameter
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Nama: ${trip.nama}",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = fontSize.sp) // Menyesuaikan font size
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Tujuan: ${trip.tujuan}",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = fontSize.sp) // Menyesuaikan font size
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Tanggal: ${trip.tanggal}",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = fontSize.sp) // Menyesuaikan font size
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Keterangan: ${trip.keterangan}",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = fontSize.sp) // Menyesuaikan font size
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { onEditTrip(trip) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("Edit")
                }

                Button(
                    onClick = { onDeleteTrip(trip.id) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Hapus")
                }

                Button(
                    onClick = { onDetailTrip(trip.id) },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Detail")
                }
            }
        }
    }
}

