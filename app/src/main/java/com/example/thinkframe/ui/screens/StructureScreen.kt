package com.example.thinkframe.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StructureScreen(
    optionATitle: String,
    optionBTitle: String,
    optionAPros: String,
    optionACons: String,
    optionARisks: String,
    optionAFuture: String,
    optionBPros: String,
    optionBCons: String,
    optionBRisks: String,
    optionBFuture: String,
    onOptionAProsChange: (String) -> Unit,
    onOptionAConsChange: (String) -> Unit,
    onOptionARisksChange: (String) -> Unit,
    onOptionAFutureChange: (String) -> Unit,
    onOptionBProsChange: (String) -> Unit,
    onOptionBConsChange: (String) -> Unit,
    onOptionBRisksChange: (String) -> Unit,
    onOptionBFutureChange: (String) -> Unit,
    onNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OptionStructureSection(
            optionTitle = "Option A: ${optionATitle.ifBlank { "A" }}",
            pros = optionAPros,
            cons = optionACons,
            risks = optionARisks,
            future = optionAFuture,
            onProsChange = onOptionAProsChange,
            onConsChange = onOptionAConsChange,
            onRisksChange = onOptionARisksChange,
            onFutureChange = onOptionAFutureChange
        )

        OptionStructureSection(
            optionTitle = "Option B: ${optionBTitle.ifBlank { "B" }}",
            pros = optionBPros,
            cons = optionBCons,
            risks = optionBRisks,
            future = optionBFuture,
            onProsChange = onOptionBProsChange,
            onConsChange = onOptionBConsChange,
            onRisksChange = onOptionBRisksChange,
            onFutureChange = onOptionBFutureChange
        )

        Button(onClick = onNext) {
            Text("Next")
        }
    }
}

@Composable
private fun OptionStructureSection(
    optionTitle: String,
    pros: String,
    cons: String,
    risks: String,
    future: String,
    onProsChange: (String) -> Unit,
    onConsChange: (String) -> Unit,
    onRisksChange: (String) -> Unit,
    onFutureChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(optionTitle)

        OutlinedTextField(
            value = pros,
            onValueChange = onProsChange,
            label = { Text("Pros") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        OutlinedTextField(
            value = cons,
            onValueChange = onConsChange,
            label = { Text("Cons") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        OutlinedTextField(
            value = risks,
            onValueChange = onRisksChange,
            label = { Text("Risks") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )

        OutlinedTextField(
            value = future,
            onValueChange = onFutureChange,
            label = { Text("What will happen in 6 months") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )
    }
}
