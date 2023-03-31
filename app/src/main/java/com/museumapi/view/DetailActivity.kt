package com.museumapi.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.museumapi.databinding.ActivityDetailBinding
import com.museumapi.repository.Repository
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityDetailBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val intent = Intent()
        val id = intent.extras?.get(ID) as Int
        request(id)
    }

    private fun request(id: Int) {
        viewModel = ViewModelProvider(
            this,
            HomeViewModel.HomeViewModelFactory(Repository())
        )[HomeViewModel::class.java]

        viewModel.getMuseumObjectById(id)

        viewModel.objetoMuseum.observe(this, Observer {
            viewBinding.ivDetail.text = it.culture

            val imagePath = it.primaryImage
            if (imagePath.isNotEmpty())
                Picasso.get().load(imagePath).into(viewBinding.ivHeader)
        })
    }

    companion object {
        private const val ID = "id"
        fun newInstance(context: Context, id: Int) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(ID, id)
            }
    }
}