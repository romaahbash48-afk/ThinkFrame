package com.example.thinkframe.ui.navigation

sealed class Screen(val route: String) {
    data object InputProblem : Screen("input_problem")
    data object Options : Screen("options")
    data object Structure : Screen("structure")
    data object FinalDecision : Screen("final_decision")
    data object History : Screen("history")
    data object DecisionDetails : Screen("decision_details/{decisionId}") {
        const val DECISION_ID_ARG = "decisionId"
        fun createRoute(decisionId: Long): String = "decision_details/$decisionId"
    }
}
