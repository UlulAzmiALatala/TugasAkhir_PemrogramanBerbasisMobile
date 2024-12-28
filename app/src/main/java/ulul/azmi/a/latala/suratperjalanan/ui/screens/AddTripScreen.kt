// file AddTripScreen.kt

package ulul.azmi.a.latala.suratperjalanan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ulul.azmi.a.latala.suratperjalanan.R
import ulul.azmi.a.latala.suratperjalanan.data.TripData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTripScreen(
    onSaveTrip: (String, String, String, String) -> Unit, // Callback untuk menyimpan perjalanan
    onCancel: () -> Unit, // Callback untuk membatalkan
    trip: TripData? = null // Perjalanan yang akan diedit (jika ada)
) {
    // State untuk menyimpan input pengguna
    var nama by remember { mutableStateOf(trip?.nama ?: "") }
    var tujuan by remember { mutableStateOf(trip?.tujuan ?: "") }
    var tanggal by remember { mutableStateOf(trip?.tanggal ?: "") }
    var keterangan by remember { mutableStateOf(trip?.keterangan ?: "") }

    // Fokus manager untuk mengelola fokus
    val focusManager = LocalFocusManager.current
    // Fokus requesters untuk setiap input
    val tujuanFocusRequester = remember { FocusRequester() }
    val tanggalFocusRequester = remember { FocusRequester() }
    val keteranganFocusRequester = remember { FocusRequester() }

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
                                text = if (trip == null) "Tambah Perjalanan Dinas" else "Edit Perjalanan Dinas",
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
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Input untuk nama perjalanan
                OutlinedTextField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = { Text("Nama Perjalanan") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { tujuanFocusRequester.requestFocus() }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(FocusRequester())
                        .padding(vertical = 4.dp)
                )

                // Input untuk tujuan perjalanan
                OutlinedTextField(
                    value = tujuan,
                    onValueChange = { tujuan = it },
                    label = { Text("Tujuan Perjalanan") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { tanggalFocusRequester.requestFocus() }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(tujuanFocusRequester)
                        .padding(vertical = 4.dp)
                )

                // Input untuk tanggal perjalanan
                OutlinedTextField(
                    value = tanggal,
                    onValueChange = { tanggal = it },
                    label = { Text("Tanggal Perjalanan") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { keteranganFocusRequester.requestFocus() }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(tanggalFocusRequester)
                        .padding(vertical = 4.dp)
                )

                // Input untuk keterangan perjalanan
                OutlinedTextField(
                    value = keterangan,
                    onValueChange = { keterangan = it },
                    label = { Text("Keterangan") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() } // Menyelesaikan input dan menyembunyikan keyboard
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(keteranganFocusRequester)
                        .padding(vertical = 4.dp)
                )

                // Tombol untuk menyimpan atau membatalkan
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { onSaveTrip(nama, tujuan, tanggal, keterangan) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Simpan", modifier = Modifier.padding(horizontal = 16.dp))
                    }

                    OutlinedButton(
                        onClick = onCancel,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Batal", modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    )
}

