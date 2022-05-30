package com.candra.trainingkopdig.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.candra.trainingkopdig.databinding.ActivityMainBinding
import com.candra.trainingkopdig.helper.Animation

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Animation.animationCardView(binding.menuOne)

        binding.menuOne.setOnClickListener {
            startActivity(Intent(this,MenuMainCoffee::class.java))
        }
    }

}