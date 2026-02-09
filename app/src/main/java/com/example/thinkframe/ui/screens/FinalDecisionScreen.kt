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
fun FinalDecisionScreen(
    finalDecisionText: String,
    onFinalDecisionTextChange: (String) -> Unit,
    onSaveDecision: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = finalDecisionText,
            onValueChange = onFinalDecisionTextChange,
            label = { Text("Which option feels more right and why?") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 4
        )

        Button(
            onClick = onSaveDecision,
            enabled = finalDecisionText.isNotBlank()
        ) {
            Text("Save Decision")
        }
    }
}
