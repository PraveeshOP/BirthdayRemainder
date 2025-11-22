package com.example.prkha2082.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.prkha2082.R

@Composable
fun DialogBox(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector = Icons.Filled.Info// Optional icon
)
{
    AlertDialog(
        icon = {
            icon?.let { Icon(it, contentDescription = "Dialog Icon") }
        },
        title = {
            Text(
                text = dialogTitle,
                fontWeight = FontWeight.Bold)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            // This is called when the user clicks outside the dialog
            // or presses the back button.
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {Text("Ja")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {Text("Nei")
            }
        }
    )
}