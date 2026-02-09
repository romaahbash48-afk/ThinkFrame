package com.example.thinkframe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thinkframe.ui.screens.DecisionDetailsScreen
import com.example.thinkframe.ui.screens.FinalDecisionScreen
import com.example.thinkframe.ui.screens.HistoryScreen
import com.example.thinkframe.ui.screens.InputProblemScreen
import com.example.thinkframe.ui.screens.OptionsScreen
import com.example.thinkframe.ui.screens.StructureScreen
import com.example.thinkframe.viewmodel.DecisionViewModel

@Composable
fun ThinkFrameNavHost(
    viewModel: DecisionViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.InputProblem.route,
        modifier = modifier
    ) {
        composable(Screen.InputProblem.route) {
            InputProblemScreen(
                problemText = viewModel.problemText,
                onProblemTextChange = { viewModel.problemText = it },
                onNext = { navController.navigate(Screen.Options.route) }
            )
        }

        composable(Screen.Options.route) {
            OptionsScreen(
                optionATitle = viewModel.optionATitle,
                optionBTitle = viewModel.optionBTitle,
                onOptionATitleChange = { viewModel.optionATitle = it },
                onOptionBTitleChange = { viewModel.optionBTitle = it },
                onNext = { navController.navigate(Screen.Structure.route) }
            )
        }

        composable(Screen.Structure.route) {
            StructureScreen(
                optionATitle = viewModel.optionATitle,
                optionBTitle = viewModel.optionBTitle,
                optionAPros = viewModel.optionAPros,
                optionACons = viewModel.optionACons,
                optionARisks = viewModel.optionARisks,
                optionAFuture = viewModel.optionAFuture,
                optionBPros = viewModel.optionBPros,
                optionBCons = viewModel.optionBCons,
                optionBRisks = viewModel.optionBRisks,
                optionBFuture = viewModel.optionBFuture,
                onOptionAProsChange = { viewModel.optionAPros = it },
                onOptionAConsChange = { viewModel.optionACons = it },
                onOptionARisksChange = { viewModel.optionARisks = it },
                onOptionAFutureChange = { viewModel.optionAFuture = it },
                onOptionBProsChange = { viewModel.optionBPros = it },
                onOptionBConsChange = { viewModel.optionBCons = it },
                onOptionBRisksChange = { viewModel.optionBRisks = it },
                onOptionBFutureChange = { viewModel.optionBFuture = it },
                onNext = { navController.navigate(Screen.FinalDecision.route) }
            )
        }

        composable(Screen.FinalDecision.route) {
            FinalDecisionScreen(
                finalDecisionText = viewModel.finalDecisionText,
                onFinalDecisionTextChange = { viewModel.finalDecisionText = it },
                onSaveDecision = {
                    viewModel.saveDecision {
                        navController.navigate(Screen.History.route) {
                            popUpTo(Screen.InputProblem.route) {
                                inclusive = false
                            }
                        }
                    }
                }
            )
        }

        composable(Screen.History.route) {
            val decisions by viewModel.history.collectAsState()
            HistoryScreen(
                decisions = decisions,
                onDecisionClick = { id ->
                    navController.navigate(Screen.DecisionDetails.createRoute(id))
                }
            )
        }

        composable(
            route = Screen.DecisionDetails.route,
            arguments = listOf(
                navArgument(Screen.DecisionDetails.DECISION_ID_ARG) {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val decisionId = backStackEntry.arguments
                ?.getLong(Screen.DecisionDetails.DECISION_ID_ARG) ?: return@composable
            val decisionFlow = remember(decisionId) { viewModel.getDecisionById(decisionId) }
            val decision by decisionFlow.collectAsState(initial = null)

            DecisionDetailsScreen(decision = decision)
        }
    }
}
