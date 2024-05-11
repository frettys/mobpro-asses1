package org.d3if3148.asses1.ui.screen

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3148.asses1.R
import org.d3if3148.asses1.database.ExpenseDb
import org.d3if3148.asses1.ui.theme.Asses1Theme
import org.d3if3148.asses1.util.ViewModelFactory

const val KEY_ID_EXPENSES = "idExpenses"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null){

    val context = LocalContext.current
    val db = ExpenseDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)

    val viewModel: DetailViewModel = viewModel(factory = factory)

    var masuk by rememberSaveable { mutableStateOf("") }
    var keluar by rememberSaveable { mutableStateOf("") }

    var kategori by rememberSaveable { mutableStateOf("") }
    var total by rememberSaveable { mutableFloatStateOf(0f)
    }

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(true){
        if (id == null) return@LaunchedEffect
        val data = viewModel.getExpense(id) ?:return@LaunchedEffect

        masuk = data.masuk
        keluar = data.keluar
        kategori = data.kategori
        total = data.total
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if(id == null){
                        Text(text = stringResource(id = R.string.tambah_pengeluaran))
                    }
                    else {
                        Text(text = stringResource(id = R.string.ubah_pengeluaran))
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        if (masuk == "" && keluar == "" || keluar.isNotEmpty() && kategori.isEmpty()){
                            Toast.makeText(context, R.string.input_invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if(id == null){
                            viewModel.insert(masuk, keluar, kategori, total)
                        }
                        else{
                            viewModel.update(id, masuk, keluar, kategori, total)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.save),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null){
                        DeleteAction { showDialog = true }
                        DisplayAlertDialog(
                            openDialog = showDialog,
                            onDismissRequest = { showDialog = false}
                        ) {
                            showDialog = false
                            viewModel.delete(id)
                            navController.popBackStack()
                        }
                    }
                }
            )
        }
    ) { padding ->
        FormExpense(
            inc = masuk,
            onIncChange = { masuk = it },
            exps = keluar,
            onExpsChange = { keluar = it},
            cat = kategori,
            onCatChange = { kategori = it},
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormExpense(
    inc: String,
    onIncChange: (String) -> Unit,
    exps: String,
    onExpsChange: (String) -> Unit,
    cat: String,
    onCatChange: (String) -> Unit,
    modifier: Modifier
) {
    val radioOptions = listOf(
        stringResource(id = R.string.fnd),
        stringResource(id = R.string.daily)
    )

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = inc,
            onValueChange = { onIncChange(it) },
            label = { Text(text = stringResource(R.string.income)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = exps,
            onValueChange = { onExpsChange(it) },
            label = { Text(text = stringResource(R.string.expense)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text ->
                ExpenseCategory(
                    label = text,
                    isSelected = cat == text,
                    modifier = Modifier
                        .selectable(
                            selected = cat == text,
                            onClick = { onCatChange(text) },
                            role = Role.RadioButton
                        ),
                    onCatChange
                )
            }
        }
    }
}

@Composable
fun ExpenseCategory(label: String, isSelected: Boolean, modifier: Modifier, onCatChange: (String) -> Unit) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = {onCatChange(label)})
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun DeleteAction(delete: () -> Unit){
    var expended by remember { mutableStateOf(false) }

    IconButton(onClick = {expended = true}) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(id = R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expended,
            onDismissRequest = { expended = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.hapus)) },
                onClick = {
                    expended = false
                    delete()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    Asses1Theme {
        DetailScreen(rememberNavController())
    }
}