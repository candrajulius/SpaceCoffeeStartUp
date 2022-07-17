package com.candra.trainingkopdig.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.candra.trainingkopdig.R
import com.candra.trainingkopdig.adapter.CoffeeAdapter
import com.candra.trainingkopdig.databinding.MenuMainCoffeeBinding
import com.candra.trainingkopdig.helper.Animation
import com.candra.trainingkopdig.helper.Constant.isDarkMode
import com.candra.trainingkopdig.model.response.ResponseModel
import com.candra.trainingkopdig.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuMainCoffee: AppCompatActivity()
{
    private lateinit var binding: MenuMainCoffeeBinding

    private val homeViewModel by viewModels<HomeViewModel>()
    private val mainAdapter by lazy { CoffeeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MenuMainCoffeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
        setHandleDarkMode()
    }
    
    private fun setHandleDarkMode(){
        binding.apply {
            backArrow.setImageResource(
                if (isDarkMode) R.drawable.ic_baseline_arrow_back_white
                else R.drawable.ic_baseline_arrow_back_ios_24
            )
        }
    }


    private fun setData(){
        with(binding){
            backCardView.setOnClickListener {
                onBackPressed()
            }
            homeViewModel.listCoffee.observe(this@MenuMainCoffee){
                if (it != null){
                    when(it){
                        is ResponseModel.isLoading -> {
                            Animation.progressBarShow(progressBar,true,rvListCoffee)
                        }
                        is ResponseModel.Success -> {
                            Animation.progressBarShow(progressBar,false,rvListCoffee)
                            mainAdapter.setData(it.data)
                        }
                        is ResponseModel.Error -> {
                            Animation.progressBarShow(progressBar,false,rvListCoffee)
                            Toast.makeText(this@MenuMainCoffee,"Terjadi error pada sistem ini",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            rvListCoffee.apply {
                layoutManager = LinearLayoutManager(this@MenuMainCoffee)
                setHasFixedSize(true)
                adapter = mainAdapter
            }
        }
    }
}