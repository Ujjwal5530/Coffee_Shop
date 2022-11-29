package com.malhotra.coffeeshop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.malhotra.coffeeshop.database.HistoryDatabase
import com.malhotra.coffeeshop.modelclass.HistoryList
import com.malhotra.coffeeshop.repository.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : HistoryRepository

    init {
        val dao = HistoryDatabase.getInstance(application).dao()
        repository = HistoryRepository(dao)
    }

    fun getOrderList() : LiveData<List<HistoryList>> {
        return repository.getOrderList()
    }

    fun insertOrder(list: HistoryList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertOrder(list)
        }
    }
}