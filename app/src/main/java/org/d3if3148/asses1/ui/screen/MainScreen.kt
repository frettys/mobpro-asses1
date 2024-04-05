package org.d3if3148.asses1.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if3148.asses1.R
import org.d3if3148.asses1.ui.theme.Asses1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    var inc by remember { mutableStateOf("") }
    var incError by remember { mutableStateOf(false) }

    var exps by remember { mutableStateOf("") }
    var expsError by remember { mutableStateOf(false) }

    val radioOptions = listOf(
        stringResource(id = R.string.fnd),
        stringResource(id = R.string.daily)
    )
    var total by remember { mutableFloatStateOf(0f) }
    var kategori by remember { mutableStateOf(radioOptions[0]) }

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.expense_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = inc,
            onValueChange = {inc = it},
            label = { Text(text = stringResource(R.string.income))},
            isError = incError,
            trailingIcon = { IconPicker(incError)},
            supportingText = { ErrorHint(incError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = exps,
            onValueChange = {exps = it},
            label = { Text(text = stringResource(R.string.expense))},
            isError = expsError,
            trailingIcon = { IconPicker(expsError)},
            supportingText = { ErrorHint(expsError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row (
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text ->
                ExpenseCategory(
                    label = text,
                    isSelected = kategori == text,
                    modifier = Modifier
                        .selectable(
                            selected = kategori == text,
                            onClick = { kategori = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    incError = (inc == "" || inc == "0")
                    expsError = (exps == "" || exps == "0")
                    if (incError || expsError) return@Button

                    total = hitungPengeluaran(inc.toFloat(), exps.toFloat())
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.hitung))
            }
            if (total != 0f) {
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp
                )
                Text(
                    text = stringResource(R.string.expense_x, total),
                    style = MaterialTheme.typography.titleLarge,
                )
                if (total <= 50000){
                    Text(
                        text = stringResource(R.string.tidak_aman),
                        style = MaterialTheme.typography.titleLarge
                    )
                } else {
                    Text(
                        text = stringResource(R.string.aman),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@Composable
fun IconPicker(isError: Boolean) {
    if (isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}

@Composable
fun ExpenseCategory(label: String, isSelected: Boolean, modifier: Modifier) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

private fun hitungPengeluaran(inc: Float, exps: Float): Float {
    return inc - exps
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    Asses1Theme {
        MainScreen()
    }
}