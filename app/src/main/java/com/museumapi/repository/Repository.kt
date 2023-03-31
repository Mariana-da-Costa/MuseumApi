package com.museumapi.repository

import android.util.Log
import com.museumapi.model.Department
import com.museumapi.model.MuseumObject
import com.museumapi.network.MetService
import com.museumapi.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

const val TAG = "Repository"

class Repository () {
    val service = RetrofitInstance.api
    lateinit var objectIDs: List<Int>
    init {
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                RetrofitInstance.api.fetchMuseumObjectIds()
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                objectIDs = response.body()!!.objectIDs
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
    }

    fun getMuseumObject(objectId: Int): MuseumObject {
        TODO()
    }

    fun getDepartments(): List<Department> {
        TODO()
    }

    fun getFavorites(): List<MuseumObject> {
        TODO()
    }

    fun getNObjects(n: Int): List<MuseumObject> {
        TODO()
    }

    fun getNObjectsByDepto(n: Int, deptoId: Int): List<MuseumObject> {
        TODO()
    }
}
