package com.malhotra.coffeeshop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.malhotra.coffeeshop.database.SummaryDatabase
import com.malhotra.coffeeshop.modelclass.SummaryList
import com.malhotra.coffeeshop.repository.SummaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SummaryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : SummaryRepository

    init {
        val dao = SummaryDatabase.getInstance(application).dao()
        repository = SummaryRepository(dao)
    }

    fun getOrder(orderNo : Int) : LiveData<List<SummaryList>> {
        return repository.getOrder(orderNo)
    }

    fun insertOrder(list: SummaryList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertOrder(list)
        }
    }

}