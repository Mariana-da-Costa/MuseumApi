package com.museumapi.view

import android.content.Context
import android.content.Intent
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

    companion object {
        fun newInstance(context: Context) = Intent(context, DetailActivity::class.java)
    }
}