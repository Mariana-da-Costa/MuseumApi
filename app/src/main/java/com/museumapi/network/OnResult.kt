package com.museumapi.network

interface OnResult<T> {
    fun onSuccess(result: T)
    fun onFailure()
}