package org.d3if3148.asses1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.d3if3148.asses1.model.Gambar
import org.d3if3148.asses1.navigation.SetupNavGraph
import org.d3if3148.asses1.ui.screen.MainScreen
import org.d3if3148.asses1.ui.theme.Asses1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Asses1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    SetupNavGraph()
                }
            }
        }
    }
}

private fun getData(): List<Gambar>{
    return listOf(
        Gambar(R.drawable.thumb_up),
        Gambar(R.drawable._d_man_hand_giving_gold_coin_with_dollar_symbol_9)
    )
}

private val data = getData()