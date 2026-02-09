package com.example.thinkframe.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "decisions")
data class DecisionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long,
    val problemText: String,
    val optionATitle: String,
    val optionBTitle: String,
    val optionAPros: String,
    val optionACons: String,
    val optionARisks: String,
    val optionAFuture: String,
    val optionBPros: String,
    val optionBCons: String,
    val optionBRisks: String,
    val optionBFuture: String,
    val finalDecisionText: String
)
