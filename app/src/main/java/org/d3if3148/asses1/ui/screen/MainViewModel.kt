package org.d3if3148.asses1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3148.asses1.database.ExpenseDao
import org.d3if3148.asses1.model.Calculate

class MainViewModel(dao: ExpenseDao) : ViewModel() {
    val data: StateFlow<List<Calculate>> = dao.getExpense().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}