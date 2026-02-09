package com.example.thinkframe.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OptionsScreen(
    optionATitle: String,
    optionBTitle: String,
    onOptionATitleChange: (String) -> Unit,
    onOptionBTitleChange: (String) -> Unit,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = optionATitle,
            onValueChange = onOptionATitleChange,
            label = { Text("Option A") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = optionBTitle,
            onValueChange = onOptionBTitleChange,
            label = { Text("Option B") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = onNext,
            enabled = optionATitle.isNotBlank() && optionBTitle.isNotBlank()
        ) {
            Text("Next")
        }
    }
}
