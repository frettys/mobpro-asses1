package org.d3if3148.asses1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense")
data class Calculate(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val masuk: String,
    val keluar: String,
    val kategori: String,
    val total: Float,
    val tanggal: String
)
