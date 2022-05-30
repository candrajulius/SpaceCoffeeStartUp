package com.candra.trainingkopdig.model.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.candra.trainingkopdig.helper.JsonHelper
import com.candra.trainingkopdig.model.response.ResponseModel
import org.json.JSONException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val jsonHelper: JsonHelper
)
{
    fun getAllCoffee(): LiveData<ResponseModel<List<Coffee>>>{
        val resultData = MutableLiveData<ResponseModel<List<Coffee>>>()
        val handler = Handler(Looper.getMainLooper())

        handler.postDelayed({
            try {
                resultData.value = ResponseModel.loading()
                val dataArray = jsonHelper.loadData()
                if (dataArray.isNotEmpty()){
                    resultData.value = ResponseModel.success(dataArray)
                }else{
                    resultData.value = ResponseModel.failed("Tidak ada data")
                }
            }catch (e: JSONException){
                resultData.value = ResponseModel.failed(e.toString())
                Log.e("RemoteDataSource", "getAllCoffee: ${e.toString()}")
            }
        },2000)

        return resultData
    }
}