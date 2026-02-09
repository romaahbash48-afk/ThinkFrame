package com.example.thinkframe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thinkframe.data.local.AppDatabase
import com.example.thinkframe.ui.navigation.ThinkFrameNavHost
import com.example.thinkframe.ui.theme.ThinkFrameTheme
import com.example.thinkframe.viewmodel.DecisionViewModel
import com.example.thinkframe.viewmodel.DecisionViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThinkFrameApp()
        }
    }
}

@Composable
private fun ThinkFrameApp() {
    ThinkFrameTheme {
        val context = LocalContext.current.applicationContext
        val database = remember { AppDatabase.getDatabase(context) }
        val decisionViewModel: DecisionViewModel = viewModel(
            factory = DecisionViewModelFactory(database.decisionDao())
        )

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            ThinkFrameNavHost(
                viewModel = decisionViewModel,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}