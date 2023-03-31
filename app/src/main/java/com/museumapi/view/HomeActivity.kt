package com.museumapi.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.museumapi.databinding.ActivityHomeBinding
import com.museumapi.model.MuseumObject
import com.museumapi.repository.Repository

class HomeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private val lista = mutableListOf<MuseumObject>()
    private val listaAdapter = HomeAdapter(lista)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewModel = ViewModelProvider(
            this,
            HomeViewModel.HomeViewModelFactory(Repository())
        )[HomeViewModel::class.java]

        viewModel.obterLista(20)

        viewModel.lista.observe(this, Observer {
            lista.addAll(it)
            listaAdapter.notifyDataSetChanged()
        })

        viewBinding.rvHome.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(viewBinding.root.context)
            adapter = listaAdapter
            this.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                startActivity(intent)
            }
        }
    }
}