package com.example.thinkframe.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DecisionDao {
    @Insert
    suspend fun insertDecision(decision: DecisionEntity)

    @Query("SELECT * FROM decisions ORDER BY timestamp DESC")
    fun getAllDecisions(): Flow<List<DecisionEntity>>

    @Query("SELECT * FROM decisions WHERE id = :id LIMIT 1")
    fun getDecisionById(id: Long): Flow<DecisionEntity?>
}
