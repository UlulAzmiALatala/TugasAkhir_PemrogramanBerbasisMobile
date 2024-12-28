// file TripDetailScreen.kt

package ulul.azmi.a.latala.suratperjalanan.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ulul.azmi.a.latala.suratperjalanan.R
import ulul.azmi.a.latala.suratperjalanan.data.TripData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailScreen(
    trip: TripData,
    onBackClick: () -> Unit
) {
    val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Surat Perjalanan Dinas") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Kop Surat dengan Logo di Samping
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_kota_palu),
                        contentDescription = "Logo Pemerintah",
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp)) // Jarak antara logo dan title
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // Geser title ke kiri
                        modifier = Modifier.fillMaxWidth(0.8f) // Mengurangi lebar kolom agar lebih rapat ke kiri
                    ) {
                        Text(
                            text = "BALAI PEMANTAUAN PEMANFAATAN HUTAN PRODUKSI",
                            style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp), // Ukuran dikecil
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "KEMENTERIAN KEHUTANAN",
                            style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp), // Ukuran dikecil
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "PEMERINTAH KOTA PALU",
                            style = MaterialTheme.typography.titleMedium.copy(fontSize = 15.sp), // Ukuran dikecil
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Jl. Ahmad Yani No. 45, Kota Palu",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Divider(color = MaterialTheme.colorScheme.primary, thickness = 2.dp)

                Spacer(modifier = Modifier.height(20.dp))

                // Teks Surat Perjalanan Dinas
                Text(
                    text = "SURAT PENUGASAN PERJALANAN DINAS",
                    style = MaterialTheme.typography.headlineSmall.copy(fontSize = 14.sp), // Ukuran dikecil
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(22.dp))

                // Bagian Informasi Surat
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Nomor: 123/PD/2024",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Lampiran: -",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Perihal: Surat Perjalanan Dinas",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Isi Surat
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Yang bertanda tangan di bawah ini:",
                        style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Nama: ${trip.nama}",
                        style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = "Tujuan: ${trip.tujuan}",
                        style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = "Tanggal: ${trip.tanggal}",
                        style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = "Keterangan: ${trip.keterangan}",
                        style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(15.dp))

                    // Justified text
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Semua biaya dalam perjalanan dinas, konsumsi, serta akomodasi " +
                                    "dalam rangka perjalanan dinas ini akan menjadi tanggung jawab " +
                                    "dari Balai Pemantauan Pemanfaatan Hutan Produksi Kota Palu " +
                                    "sesuai dengan peraturan yang berlaku.",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(13.dp))

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Demikian harap Saudara dapat melaksanakannya serta kami memohon " +
                                    "ke semua pihak agar membantu guna kelancaran pelaksanaan perjalanan " +
                                    "dinas tersebut.",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(34.dp))

                // Footer Surat
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Palu, $currentDate", // Menggunakan tanggal real-time
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.End)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Kepala Dinas",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.End)
                    )
                    Spacer(modifier = Modifier.height(64.dp))
                    Divider(
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .width(142.dp)
                            .align(Alignment.End),
                        thickness = 1.dp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Dr. Ulul Azmi A. Latala",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
        }
    )
}

