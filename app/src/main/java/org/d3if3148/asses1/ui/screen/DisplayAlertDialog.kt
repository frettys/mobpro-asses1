package org.d3if3148.asses1.ui.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.d3if3148.asses1.R
import org.d3if3148.asses1.ui.theme.Asses1Theme

@Composable
fun DisplayAlertDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    if (openDialog){
        AlertDialog(
            text = { Text(text = stringResource(R.string.pesan_hapus))},
            confirmButton = {
                TextButton(onClick = { onConfirmation() }) {
                    Text(text = stringResource(R.string.hapus))
                }
            },
            dismissButton = {
                TextButton(onClick = {onDismissRequest() }) {
                    Text(text = stringResource(R.string.batal))
                }
            },
            onDismissRequest = {onDismissRequest()}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    Asses1Theme {
        DisplayAlertDialog(
            openDialog = true,
            onDismissRequest = {},
            onConfirmation = {}
        )
    }
}