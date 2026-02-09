package com.example.thinkframe.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
fun HistoryScreen(
    decisions: List<DecisionEntity>,
    onDecisionClick: (Long) -> Unit
) {
    if (decisions.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("No saved decisions yet.")
        }
        return
    }

    val formatter = remember {
        SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = decisions,
            key = { it.id }
        ) { decision ->
            val shortProblem = remember(decision.problemText) {
                if (decision.problemText.length <= 60) {
                    decision.problemText
                } else {
                    decision.problemText.take(60) + "..."
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDecisionClick(decision.id) }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = formatter.format(Date(decision.timestamp)),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = shortProblem,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
