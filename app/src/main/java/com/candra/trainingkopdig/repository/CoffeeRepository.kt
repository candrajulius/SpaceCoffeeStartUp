package com.candra.trainingkopdig.repository

import androidx.lifecycle.LiveData
import com.candra.trainingkopdig.model.data.Coffee
import com.candra.trainingkopdig.model.data.RemoteDataSource
import com.candra.trainingkopdig.model.response.ResponseModel
import javax.inject.Inject

class CoffeeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
)
{
    fun getAllCoffee(): LiveData<ResponseModel<List<Coffee>>> = remoteDataSource.getAllCoffee()
}