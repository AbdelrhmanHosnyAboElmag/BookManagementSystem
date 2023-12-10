package com.example.bookstoremanageruser.presentation.ui.compound

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun OptionMenu(
    clickItem: (Triple<Boolean, Boolean, Boolean>) -> Unit,
    clickAddBook: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Sort") },
                onClick = { clickItem(Triple(true, false, false)) }
            )
            DropdownMenuItem(
                text = { Text("subscribe ") },
                onClick = { clickItem(Triple(false, true, false)) }
            )
            DropdownMenuItem(
                text = { Text("Save") },
                onClick = { clickItem(Triple(false, false, true)) }
            )
            DropdownMenuItem(
                text = { Text("ADD BOOK!") },
                onClick = { clickAddBook() }
            )
        }
    }
}