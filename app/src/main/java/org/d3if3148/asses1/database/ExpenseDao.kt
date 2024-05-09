package org.d3if3148.asses1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3148.asses1.model.Calculate

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insert(calculate: Calculate)

    @Update
    suspend fun update(calculate: Calculate)

    @Query("SELECT * FROM expense ORDER BY tanggal")
    fun getExpense(): Flow<List<Calculate>>

    @Query("SELECT * FROM expense WHERE id = :id")
    suspend fun getExpenseById(id: Long): Calculate?
}