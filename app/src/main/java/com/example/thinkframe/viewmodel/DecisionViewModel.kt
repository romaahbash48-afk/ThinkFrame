package com.example.thinkframe.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thinkframe.data.local.DecisionDao
import com.example.thinkframe.data.local.DecisionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DecisionViewModel(
    private val decisionDao: DecisionDao
) : ViewModel() {

    var problemText by mutableStateOf("")
    var optionATitle by mutableStateOf("")
    var optionBTitle by mutableStateOf("")

    var optionAPros by mutableStateOf("")
    var optionACons by mutableStateOf("")
    var optionARisks by mutableStateOf("")
    var optionAFuture by mutableStateOf("")

    var optionBPros by mutableStateOf("")
    var optionBCons by mutableStateOf("")
    var optionBRisks by mutableStateOf("")
    var optionBFuture by mutableStateOf("")

    var finalDecisionText by mutableStateOf("")

    val history = decisionDao
        .getAllDecisions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun getDecisionById(id: Long): Flow<DecisionEntity?> {
        return decisionDao.getDecisionById(id)
    }

    fun saveDecision(onSaved: () -> Unit) {
        viewModelScope.launch {
            decisionDao.insertDecision(
                DecisionEntity(
                    timestamp = System.currentTimeMillis(),
                    problemText = problemText.trim(),
                    optionATitle = optionATitle.trim(),
                    optionBTitle = optionBTitle.trim(),
                    optionAPros = optionAPros.trim(),
                    optionACons = optionACons.trim(),
                    optionARisks = optionARisks.trim(),
                    optionAFuture = optionAFuture.trim(),
                    optionBPros = optionBPros.trim(),
                    optionBCons = optionBCons.trim(),
                    optionBRisks = optionBRisks.trim(),
                    optionBFuture = optionBFuture.trim(),
                    finalDecisionText = finalDecisionText.trim()
                )
            )
            clearDraft()
            onSaved()
        }
    }

    private fun clearDraft() {
        problemText = ""
        optionATitle = ""
        optionBTitle = ""
        optionAPros = ""
        optionACons = ""
        optionARisks = ""
        optionAFuture = ""
        optionBPros = ""
        optionBCons = ""
        optionBRisks = ""
        optionBFuture = ""
        finalDecisionText = ""
    }
}
