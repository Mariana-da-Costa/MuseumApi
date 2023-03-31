package com.museumapi.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.museumapi.model.MuseumObject
import com.museumapi.repository.Repository

val objetoMuseum = MutableLiveData<MuseumObject>()

class HomeViewModel(private val repository: Repository) : ViewModel() {
    val lista = MutableLiveData<List<MuseumObject>>()
    val objetoMuseum = MutableLiveData<MuseumObject>()
    fun obterLista(n: Int) {
        repository.getNObjects(n).apply {
            lista.value = this
        }
    }

    fun getMuseumObjectById(id: Int) {
        repository.getMuseumObject(id).apply {
            objetoMuseum.value = this
        }
    }

    class HomeViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }
}