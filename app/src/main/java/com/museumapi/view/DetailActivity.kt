package com.museumapi.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.museumapi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}