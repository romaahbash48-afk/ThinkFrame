package com.example.thinkframe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.thinkframe.data.local.DecisionDao

class DecisionViewModelFactory(
    private val decisionDao: DecisionDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DecisionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DecisionViewModel(decisionDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
