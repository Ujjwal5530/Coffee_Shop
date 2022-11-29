package com.malhotra.coffeeshop.viewmodel

import android.app.Application
import androidx.core.location.LocationRequestCompat
import androidx.lifecycle.*
import com.malhotra.coffeeshop.database.CartDatabase
import com.malhotra.coffeeshop.modelclass.CartList
import com.malhotra.coffeeshop.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : CartRepository

    init {
        val dao = CartDatabase.getDatabaseInstance(application).dao()
        repository = CartRepository(dao)
    }

    fun getList() : LiveData<List<CartList>> {
        return repository.getList()
    }

    fun insertList(list: CartList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertList(list)
        }

    }

    fun totalItems() : Int {
        return repository.totalItems()
    }

    fun deleteList() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteList()
        }
    }

    fun deleteItem(list: CartList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(list)
        }
    }

    fun updateQuantity(quantity : Int, id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateQuantity(quantity, id)
        }
    }

    fun totalPrice() : Double {
        return repository.totalPrice()
    }
}