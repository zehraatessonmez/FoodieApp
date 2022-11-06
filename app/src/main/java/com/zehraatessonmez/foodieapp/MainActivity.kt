package com.zehraatessonmez.foodieapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zehraatessonmez.foodieapp.ui.fragment.AnasayfaFragment
import dagger.hilt.android.AndroidEntryPoint




@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
    }



}