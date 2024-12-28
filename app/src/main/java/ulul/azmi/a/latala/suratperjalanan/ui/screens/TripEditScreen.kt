// file TripEditScreen.kt

package ulul.azmi.a.latala.suratperjalanan.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController
import ulul.azmi.a.latala.suratperjalanan.data.TripData

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TripEditScreen(
    trip: TripData,
    onSaveEdit: (TripData) -> Unit,
    navController: NavController
) {
    var updatedTrip by remember { mutableStateOf(trip) }
    val focusManager = LocalFocusManager.current

    // Create FocusRequesters for each input field
    val destinationFocusRequester = remember { FocusRequester() }
    val dateFocusRequester = remember { FocusRequester() }
    val notesFocusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Perjalanan Dinas",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = updatedTrip.nama,
                    onValueChange = { updatedTrip = updatedTrip.copy(nama = it) },
                    label = { Text("Nama") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { destinationFocusRequester.requestFocus() }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(FocusRequester())
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = updatedTrip.tujuan,
                    onValueChange = { updatedTrip = updatedTrip.copy(tujuan = it) },
                    label = { Text("Tujuan") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { dateFocusRequester.requestFocus() }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(destinationFocusRequester)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = updatedTrip.tanggal,
                    onValueChange = { updatedTrip = updatedTrip.copy(tanggal = it) },
                    label = { Text("Tanggal") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { notesFocusRequester.requestFocus() }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(dateFocusRequester)
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = updatedTrip.keterangan,
                    onValueChange = { updatedTrip = updatedTrip.copy(keterangan = it) },
                    label = { Text("Keterangan") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() } // Dismiss the keyboard
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(notesFocusRequester)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onSaveEdit(updatedTrip)
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Simpan Perubahan", color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    )
}
