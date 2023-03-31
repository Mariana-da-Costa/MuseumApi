package com.museumapi.repository

import android.util.Log
import com.museumapi.model.Department
import com.museumapi.model.MuseumObject
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
                service.fetchMuseumObjectIds()
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

    fun getMuseumObject(objectID: Int): MuseumObject? {
        var museumObject: MuseumObject? = null
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                service.fetchMuseumObject(objectID)
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                museumObject = response.body()!!
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
        return museumObject
    }

    fun getDepartments(): List<Department>? {
        var departmentList: List<Department>? = null
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                service.fetchDepartments()
            } catch(e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if(response.isSuccessful && response.body() != null) {
                departmentList = response.body()!!.departments
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
        return departmentList
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
