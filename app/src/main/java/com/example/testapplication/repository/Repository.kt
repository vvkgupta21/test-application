package com.example.testapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapplication.model.PostDataItem
import com.example.testapplication.network.TestApi

class Repository(private val service: TestApi) {
    suspend fun getData(): LiveData<ArrayList<PostDataItem>> {
        val data = MutableLiveData<ArrayList<PostDataItem>>()
        val response = service.webService.getDataAsync().await()
        data.value = response
        return data
    }
}