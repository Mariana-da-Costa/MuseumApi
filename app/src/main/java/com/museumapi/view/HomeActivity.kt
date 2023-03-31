package com.museumapi.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.museumapi.databinding.ActivityDetailBinding
import com.museumapi.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        //variavel provisoria
        val list: List<Int> = listOf(1)

        viewBinding.rvHome.apply {
            layoutManager = LinearLayoutManager(viewBinding.root.context)
            adapter = HomeAdapter(list)
            this.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                startActivity(intent)
            }
        }

    }
}