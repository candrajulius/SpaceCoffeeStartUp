package com.candra.trainingkopdig.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.candra.trainingkopdig.R
import com.candra.trainingkopdig.databinding.ActivityDetailBinding
import com.candra.trainingkopdig.helper.Animation
import com.candra.trainingkopdig.helper.Animation.EXTRA_DATA
import com.candra.trainingkopdig.model.data.Coffee

class DetailActivity: AppCompatActivity()
{
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val data = intent.getParcelableExtra<Coffee>(EXTRA_DATA)
        getDataAllCoffee(data)
        binding.backCardView.setOnClickListener {
            onBackPressed()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun getDataAllCoffee(data: Coffee?) {
        data?.let { data ->
            with(binding){
                supportActionBar?.title = data.name
                content.tvDetailDescription.text = data.description
                ivDetailImage.load(data.photo){
                    crossfade(600)
                    crossfade(true)
                    error(R.drawable.ic_baseline_broken_image_24)
                    placeholder(R.drawable.ic_baseline_image_24)
                }
                val dataBtn = "Beli dengan harga ${data.cost} / ${data.price}"
                content.btnPrice.text = dataBtn
                content.btnPrice.setOnClickListener {
                    Toast.makeText(this@DetailActivity, dataBtn,Toast.LENGTH_SHORT).show()
                }
                cart.setOnClickListener {
                    val intentData = Intent(this@DetailActivity,CartActivity::class.java).apply {
                        putExtra("data",data.name)
                    }.also { startActivity(it) }
                }
            }
        }
    }

}