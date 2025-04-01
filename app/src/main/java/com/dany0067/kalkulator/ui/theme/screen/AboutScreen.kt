package com.dany0067.kalkulator.ui.theme.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dany0067.kalkulator.R
import com.dany0067.kalkulator.ui.theme.theme.KalkulatorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController) {
    var isEnglish by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.tentang_aplikasi))
                },
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, // Align vertically
                        horizontalArrangement = Arrangement.Center // Center the content horizontally
                    ) {
                        // Switch for language selection
                        Text(
                            text = if (isEnglish) "ENG" else "IDN",
                            modifier = Modifier.padding(end = 8.dp),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Switch(
                            checked = isEnglish,
                            onCheckedChange = { isEnglish = it },
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp), // Space out text and image
            horizontalAlignment = Alignment.CenterHorizontally // Center the content horizontally
        ) {
            Text(
                text = if (isEnglish) {
                    "This is a Simple Calculator App that can help you solve math operations, just enter 2 numbers, and select one of the math operations."
                } else {
                    "Ini merupakan Aplikasi Kalkulator Sederhana yang dapat membantu anda menyelesaikan operasi matematika, cukup masukkan 2 angka, dan pilih salah satu operasi matematika."
                },
                style = MaterialTheme.typography.bodyMedium
            )

            Image(
                painter = painterResource(id = R.drawable.wkeenan),
                contentDescription = "Calculator image",
                modifier = Modifier.padding(top = 5.dp)
            )

            Text(
                text = "Disini ada foto saya bersama Keenan ( Iconic Bandung )"
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AboutScreenPreview() {
    KalkulatorTheme {
        AboutScreen(rememberNavController())
    }
}