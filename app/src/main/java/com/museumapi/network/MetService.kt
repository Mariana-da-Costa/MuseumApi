package com.museumapi.network

import com.museumapi.model.DepartmentList
import com.museumapi.model.MuseumObject
import com.museumapi.model.MuseumObjectIdList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetService {

    @GET("objects/{objectID}")
    suspend fun fetchMuseumObject(@Path("objectID") objectId: Int): Response<MuseumObject>

    @GET("departments")
    suspend fun fetchDepartments(): Response<DepartmentList>

    @GET("objects")
    suspend fun fetchMuseumObjectIdsByDepto(@Query("departmentIds") deptoId: Int): Response<MuseumObjectIdList>

    @GET("objects")
    suspend fun fetchMuseumObjectIds(): Response<MuseumObjectIdList>

}