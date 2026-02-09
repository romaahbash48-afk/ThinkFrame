package com.example.thinkframe.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thinkframe.data.local.DecisionEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DecisionDetailsScreen(
    decision: DecisionEntity?
) {
    if (decision == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Decision not found.")
        }
        return
    }

    val formatter = remember {
        SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DetailRow("Date", formatter.format(Date(decision.timestamp)))
        DetailRow("Problem", decision.problemText)
        DetailRow("Option A", decision.optionATitle)
        DetailRow("Option B", decision.optionBTitle)

        Text(
            text = "Option A Structure",
            style = MaterialTheme.typography.titleMedium
        )
        DetailRow("Pros", decision.optionAPros)
        DetailRow("Cons", decision.optionACons)
        DetailRow("Risks", decision.optionARisks)
        DetailRow("What will happen in 6 months", decision.optionAFuture)

        Text(
            text = "Option B Structure",
            style = MaterialTheme.typography.titleMedium
        )
        DetailRow("Pros", decision.optionBPros)
        DetailRow("Cons", decision.optionBCons)
        DetailRow("Risks", decision.optionBRisks)
        DetailRow("What will happen in 6 months", decision.optionBFuture)

        Text(
            text = "Final Decision",
            style = MaterialTheme.typography.titleMedium
        )
        DetailRow("Which option feels more right and why?", decision.finalDecisionText)
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = value.ifBlank { "-" },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
