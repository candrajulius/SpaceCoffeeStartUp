package com.candra.trainingkopdig.helper

import android.content.Context
import com.candra.trainingkopdig.R
import com.candra.trainingkopdig.model.data.Coffee
import org.json.JSONObject
import java.io.IOException

class JsonHelper (private val context: Context)
{

    private var name = ""
    private var description = ""
    private var cost = ""
    private var price = ""
    private var photo = ""
    private var growing_area = ""

    private fun parsingFileToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.data).bufferedReader()
                .use { it.readText() }
        }catch (ioException: IOException){
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadData(): List<Coffee>{
        val list = ArrayList<Coffee>()
        val listArray = JSONObject(parsingFileToString().toString()).getJSONArray("data_coffee")
        for (i in 0 until listArray.length()){
            val jsonObject = listArray.getJSONObject(i)
           val data = listArray.getJSONObject(i).apply {
                name = getString("name")
                description = getString("description")
                cost = getString("cost")
                price = getString("price")
                photo = getString("photo")
                growing_area = getString("growing_area")
            }
            val responseModel = Coffee(
                name = name,
                description = description,
                cost = cost,
                price = price,
                photo = photo,
                growing_area = growing_area
            )
            list.add(responseModel)
        }
        return list
    }
}