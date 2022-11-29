package com.malhotra.coffeeshop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.malhotra.coffeeshop.database.ProfileDatabase
import com.malhotra.coffeeshop.modelclass.ProfileInfo
import com.malhotra.coffeeshop.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : ProfileRepository

    init {
        val dao = ProfileDatabase.getInstance(application).dao()
        repository = ProfileRepository(dao)
    }

    fun getInfo(id : Int) : LiveData<ProfileInfo> {
        return repository.getInfo(id)
    }

    fun getData() : LiveData<List<ProfileInfo>> {
        return repository.getData()
    }

    fun insertInfo(list : ProfileInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertInfo(list)
        }
    }

    fun updateInfo(list: ProfileInfo) {
        viewModelScope.launch(Dispatchers.IO)  {
            repository.updateInfo(list)
        }
    }
}