package org.d3if3148.asses1.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3148.asses1.R
import org.d3if3148.asses1.navigation.Screen
import org.d3if3148.asses1.ui.theme.Asses1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
//    var inc by rememberSaveable { mutableStateOf("") }
//    var incError by rememberSaveable { mutableStateOf(false) }
//
//    var exps by rememberSaveable { mutableStateOf("") }
//    var expsError by rememberSaveable { mutableStateOf(false) }
//
//    val radioOptions = listOf(
//        stringResource(id = R.string.fnd),
//        stringResource(id = R.string.daily)
//    )
//    var total by rememberSaveable { mutableIntStateOf(0) }
//    var kategori by rememberSaveable { mutableStateOf(radioOptions[0]) }
//
//    val context = LocalContext.current

    Text(
        text = stringResource(id = R.string.expense_intro),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth()
    )

//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Button(
//                onClick = {
//                    incError = (inc == "" || inc == "0")
//                    expsError = (exps == "" || exps == "0")
//                    if (incError || expsError) return@Button
//
//                    total = hitungPengeluaran(inc.toInt(), exps.toInt())
//                },
//                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
//                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
//            ) {
//                Text(text = stringResource(R.string.hitung))
//            }
//            if (total != 0) {
//                Divider(
//                    modifier = Modifier.padding(vertical = 8.dp),
//                    thickness = 1.dp
//                )
//                Text(
//                    textAlign = TextAlign.Center,
//                    text = stringResource(R.string.expense_x, total),
//                    style = MaterialTheme.typography.titleLarge,
//                )
//                if (total <= 50000){
//                    Text(
//                        textAlign = TextAlign.Center,
//                        text = stringResource(R.string.tidak_aman),
//                        style = MaterialTheme.typography.titleLarge,
//                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)
//                    )
//                    Image(
//                        painter = painterResource(R.drawable._d_man_hand_giving_gold_coin_with_dollar_symbol_9),
//                        contentDescription = stringResource(R.string.gambar, R.string.giving_coin),
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier.size(132.dp)
//                    )
//                } else {
//                    Text(
//                        textAlign = TextAlign.Center,
//                        text = stringResource(R.string.aman),
//                        style = MaterialTheme.typography.titleLarge,
//                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)
//                    )
//                    Image(
//                        painter = painterResource(R.drawable._d_render_hand_high_five_gesture_team_work_clap_9),
//                        contentDescription = stringResource(R.string.gambar, R.string.high_five),
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier.size(132.dp)
//                    )
//                }
//                Button(
//                    onClick = {
//                        shareData(
//                            context = context,
//                            message = context.getString(R.string.bagikan_template, inc, exps, total.toString(),
//                        )
//                        )
//                    },
//                    modifier = Modifier.padding(top = 8.dp),
//                    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
//                ) {
//                    Text(text = stringResource(R.string.bagikan))
//                }
//            }
//        }
}
//@Composable
//fun IconPicker(isError: Boolean) {
//    if (isError){
//        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
//    }
//}

//@Composable
//fun ErrorHint(isError: Boolean) {
//    if (isError){
//        Text(text = stringResource(R.string.input_invalid))
//    }
//}

//@Composable
//fun ExpenseCategory(label: String, isSelected: Boolean, modifier: Modifier) {
//    Row (
//        modifier = modifier,
//        verticalAlignment = Alignment.CenterVertically
//    ){
//        RadioButton(selected = isSelected, onClick = null)
//        Text(
//            text = label,
//            style = MaterialTheme.typography.bodyLarge,
//            modifier = Modifier.padding(start = 8.dp)
//        )
//    }
//}
//
//private fun hitungPengeluaran(inc: Int, exps: Int): Int {
//    return inc - exps
//}
//
//private fun shareData(context: Context, message: String){
//    val shareIntent = Intent(Intent.ACTION_SEND).apply {
//        type = "text/plain"
//        putExtra(Intent.EXTRA_TEXT, message)
//    }
//    if (shareIntent.resolveActivity(context.packageManager) != null){
//        context.startActivity(shareIntent)
//    }
//}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    Asses1Theme {
        MainScreen(rememberNavController())
    }
}