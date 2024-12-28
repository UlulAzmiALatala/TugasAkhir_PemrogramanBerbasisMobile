package ulul.azmi.a.latala.suratperjalanan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.draw.alpha
import ulul.azmi.a.latala.suratperjalanan.R

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
	// Animasi untuk logo agar lebih dinamis
	val logoAlpha = animateFloatAsState(
		targetValue = 1f,
		animationSpec = tween(durationMillis = 2000)
	)

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		// Logo dengan efek transparansi
		Image(
			painter = painterResource(id = R.drawable.logo_kementerian),
			contentDescription = "Logo Kementerian",
			modifier = Modifier
				.size(120.dp)
				.alpha(logoAlpha.value) // Menambahkan efek transparansi
		)

		Spacer(modifier = Modifier.height(30.dp))

		// Teks dengan font yang lebih besar dan sedikit animasi
		Text(
			text = "Selamat Datang di Aplikasi Surat Perjalanan Dinas",
			style = MaterialTheme.typography.headlineLarge.copy(
				fontSize = 26.sp,
				color = MaterialTheme.colorScheme.primary
			),
			textAlign = TextAlign.Center
		)

		Spacer(modifier = Modifier.height(40.dp))

		// Indikator loading dengan warna lebih cerah
		CircularProgressIndicator(
			modifier = Modifier.size(60.dp),
			color = MaterialTheme.colorScheme.secondary
		)
	}

	// Mengatur timeout untuk berpindah ke layar berikutnya
	LaunchedEffect(Unit) {
		delay(3000) // Tampilkan splash screen selama 3 detik
		onTimeout() // Panggil callback untuk berpindah ke layar berikutnya
	}
}
