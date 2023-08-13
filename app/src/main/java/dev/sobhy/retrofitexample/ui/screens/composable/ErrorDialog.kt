package dev.sobhy.retrofitexample.ui.screens.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorDialog(show: Boolean, message: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    if (!show) return
    AlertDialog(
        title = { Text(text = "ERROR") },
        text = { Text(text = message) },
        onDismissRequest = onDismiss::invoke,
        confirmButton = {
            Button(onClick = onConfirm::invoke) {
                Text(text = "Confirm")
            }
        })
}