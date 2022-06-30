package com.candra.trainingkopdig.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import coil.load
import com.candra.trainingkopdig.R
import com.candra.trainingkopdig.databinding.ActivityMainBinding
import com.candra.trainingkopdig.helper.Animation

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Animation.animationCardView(binding.menuOne)

//        binding.imageOne.load("https://raw.githubusercontent.com/candrajulius/SpaceCoffeeStartUp/main/semua_coffe.jpeg")
//        {
//            crossfade(true)
//            crossfade(600)
//            error(R.drawable.ic_baseline_broken_image_24)
//            placeholder(R.drawable.ic_baseline_image_24)
//        }

        binding.menuOne.background = ContextCompat.getDrawable(this@MainActivity,R.drawable.semua_coffe)

        binding.menuOne.setOnClickListener {
            startActivity(Intent(this,MenuMainCoffee::class.java))
        }
    }

}