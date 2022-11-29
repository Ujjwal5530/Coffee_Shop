package com.malhotra.coffeeshop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.malhotra.coffeeshop.database.FavDatabase
import com.malhotra.coffeeshop.modelclass.FavList
import com.malhotra.coffeeshop.repository.FavRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : FavRepository

    init {
        val dao = FavDatabase.getInstance(application).dao()
        repository = FavRepository(dao)
    }

    fun getFavList() : LiveData<List<FavList>> {
        return repository.getFavList()
    }

    fun insertFav(list: FavList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFav(list)
        }
    }

    fun updateFav(list: FavList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateList(list)
        }
    }

    fun deleteFav(list: FavList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFav(list)
        }
    }
}
