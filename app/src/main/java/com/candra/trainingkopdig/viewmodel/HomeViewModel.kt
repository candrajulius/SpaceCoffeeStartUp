package com.candra.trainingkopdig.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.candra.trainingkopdig.model.data.Coffee
import com.candra.trainingkopdig.model.response.ResponseModel
import com.candra.trainingkopdig.repository.CoffeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CoffeeRepository
) : ViewModel()
{
    val listCoffee = repository.getAllCoffee()
}