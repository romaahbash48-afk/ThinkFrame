package com.example.thinkframe.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InputProblemScreen(
    problemText: String,
    onProblemTextChange: (String) -> Unit,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = problemText,
            onValueChange = onProblemTextChange,
            label = { Text("What decision are you making?") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors()
        )

        Button(
            onClick = onNext,
            enabled = problemText.isNotBlank()
        ) {
            Text("Next")
        }
    }
}
