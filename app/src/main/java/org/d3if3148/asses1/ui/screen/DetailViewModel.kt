package org.d3if3148.asses1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3148.asses1.database.ExpenseDao
import org.d3if3148.asses1.model.Calculate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: ExpenseDao) : ViewModel (){
    private val formatter = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.US)
    fun insert(masuk: String, keluar: String, kategori: String, total: Float){
        val calculate = Calculate(
            masuk = masuk,
            keluar = keluar,
            kategori = kategori,
            total = total,
            tanggal = formatter.format(Date())
        )

        viewModelScope.launch (Dispatchers.IO){
            dao.insert(calculate)
        }
    }

    suspend fun getExpense(id: Long): Calculate? {
        return dao.getExpenseById(id)
    }

    fun update(id: Long, masuk: String, keluar: String, kategori: String, total: Float){
        val calculate = Calculate(
            id = id,
            masuk = masuk,
            keluar = keluar,
            kategori = kategori,
            total = total,
            tanggal = formatter.format(Date())
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(calculate)
        }
    }

    fun delete(id: Long){
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}