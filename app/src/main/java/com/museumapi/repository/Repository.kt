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
    val objectIDs = getObjectIDs(-1)

    private fun getObjectIDs(deptoID: Int = -1): List<Int>? {
        var objectIDs: List<Int>? = null
        CoroutineScope(Dispatchers.IO).launch {
            val response = try {
                if (deptoID == -1)
                    service.fetchMuseumObjectIds()
                else
                    service.fetchMuseumObjectIdsByDepto(deptoID)
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                return@launch
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                objectIDs = response.body()!!.objectIDs
            } else {
                Log.e(TAG, "Response not successful")
            }
        }
        return objectIDs
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
        val museumObjects = mutableListOf<MuseumObject>()
        while (museumObjects.size < n) {
            val objectID = objectIDs?.random() ?: (1..400_000).random()
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
                    museumObjects.add(response.body()!!)
                } else {
                    Log.e(TAG, "Response not successful")
                }
            }
        }
        return museumObjects.toList()
    }

    fun getNObjectsByDepto(n: Int, deptoId: Int): List<MuseumObject> {
        val departmentObjects = getObjectIDs(deptoId)
        val museumObjects = mutableListOf<MuseumObject>()
        while (museumObjects.size < n) {
            val objectID = objectIDs?.random() ?: (1..400_000).random()
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
                    museumObjects.add(response.body()!!)
                } else {
                    Log.e(TAG, "Response not successful")
                }
            }
        }
        return museumObjects.toList()
    }
}
