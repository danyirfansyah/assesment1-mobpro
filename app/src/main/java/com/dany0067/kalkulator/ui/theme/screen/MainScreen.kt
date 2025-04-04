package com.dany0067.kalkulator.ui.theme.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dany0067.kalkulator.R
import com.dany0067.kalkulator.navigation.Screen
import com.dany0067.kalkulator.ui.theme.theme.KalkulatorTheme
import java.text.DecimalFormat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    var selectedOperation by rememberSaveable { mutableStateOf("") }
    var isEnglish by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)

                        ) {
                            Switch(
                                checked = isEnglish,
                                onCheckedChange = { isEnglish = it }
                            )
                            Text(
                                text = if (isEnglish) "ENG" else "IDN",
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        IconButton(onClick = {
                            navController.navigate(Screen.About.route)
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = stringResource(R.string.tentang_aplikasi),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        KalkulatorTheme {
            ScreenContent(
                modifier = Modifier.padding(innerPadding),
                selectedOperation = selectedOperation,
                onOperationChange = { selectedOperation = it },
                onReset = {
                    selectedOperation = ""
                },
                isEnglish = isEnglish
            )
        }
    }
}

@Composable
fun ScreenContent(
    modifier: Modifier = Modifier,
    selectedOperation: String,
    onOperationChange: (String) -> Unit,
    onReset: () -> Unit,
    isEnglish: Boolean
) {
    var angka1 by rememberSaveable { mutableStateOf("") }
    var angka2 by rememberSaveable { mutableStateOf("") }
    var hasil by rememberSaveable { mutableStateOf("") }

    var angka1Error by rememberSaveable { mutableStateOf(false) }
    var angka2Error by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isEnglish) {
                "This app is a simple calculator app where you only need to input 2 numbers and choose a mathematical operation such as addition, subtraction, multiplication, and division."
            } else {
                "Aplikasi ini merupakan aplikasi Kalkulator sederhana, yang dimana kalian cukup input 2 angka saja, dan dapat memilih Operasi Matematika yaitu Pertambahan, Pengurangan, Perkalian, dan Pembagian."
            },
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 16.sp),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = angka1,
            onValueChange = { angka1 = it },
            trailingIcon = { IconPicker(angka1Error, "") },
            supportingText = { ErrorHint(angka1Error) },
            isError = angka1Error,
            label = { Text(if (isEnglish) "First number" else "Angka pertama") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = angka2,
            onValueChange = { angka2 = it },
            trailingIcon = { IconPicker(angka2Error, "") },
            supportingText = { ErrorHint(angka2Error) },
            isError = angka2Error,
            label = { Text(if (isEnglish) "Second number" else "Angka kedua") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val operations = listOf(
                "Pertambahan" to R.drawable.icon_plus,
                "Pengurangan" to R.drawable.icon_minus,
                "Perkalian" to R.drawable.icon_multiply,
                "Pembagian" to R.drawable.icon_divide
            )
            operations.forEach { (operation, imageRes) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedOperation == operation,
                        onClick = { onOperationChange(operation) }
                    )
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = operation,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    angka1Error = angka1.isBlank()
                    angka2Error = angka2.isBlank() || (angka2 == "0" && selectedOperation == "/")

                    if (angka1Error || angka2Error) return@Button

                    val a = angka1.toFloat()
                    val b = angka2.toFloat()

                    val result = hitung(a, b, mapOperationToEnglish(selectedOperation))
                    hasil = if (selectedOperation == "/") {
                        if (result % 1 == 0f) result.toInt().toString()
                        else DecimalFormat("#.##").format(result)
                    } else {
                        result.toInt().toString()
                    }
                },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = if (isEnglish) "Calculate" else "Hitung")
            }

            Button(
                onClick = {
                    angka1 = ""
                    angka2 = ""
                    hasil = ""
                    onReset()
                },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = if (isEnglish) "Reset" else "Reset")
            }
        }

        if (hasil.isNotEmpty() || selectedOperation.isNotEmpty()) {
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = if (isEnglish) "Result: $hasil" else "Hasil: $hasil",
                style = MaterialTheme.typography.titleLarge
            )

            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = if (isEnglish) {
                            "The result of $angka1 $selectedOperation $angka2 is $hasil"
                        } else {
                            "Hasil dari $angka1 $selectedOperation $angka2 adalah $hasil"
                        }
                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = if (isEnglish) "Share" else "Bagikan")
            }
        }
    }
}

private fun mapOperationToEnglish(operation: String): String {
    return when (operation) {
        "Pertambahan" -> "plus"
        "Pengurangan" -> "minus"
        "Perkalian" -> "multiply"
        "Pembagian" -> "divide"
        else -> ""
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

private fun hitung(a: Float, b: Float, op: String): Float {
    return when (op) {
        "plus" -> a + b
        "minus" -> a - b
        "multiply" -> a * b
        "divide" -> a / b
        else -> 0f
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    KalkulatorTheme {
        MainScreen(rememberNavController())
    }
}
