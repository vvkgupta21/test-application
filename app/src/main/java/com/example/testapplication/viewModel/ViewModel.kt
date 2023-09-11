package com.example.testapplication.viewModel

import androidx.lifecycle.ViewModel
import com.example.testapplication.model.PostDataItem
import com.example.testapplication.network.apiUtils.StateLiveData
import com.example.testapplication.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PostViewModel(private val repository: Repository) : ViewModel() {

    lateinit var viewModelJob: Job

    fun getPostData(): StateLiveData<ArrayList<PostDataItem>> {
        val data = StateLiveData<ArrayList<PostDataItem>>()
        data.postLoading()
        viewModelJob = CoroutineScope(Job() + Dispatchers.Main).launch {
            try {
                val response = repository.getData()
                response.value?.let { data.postSuccess(data = it) }
            }catch (e: Exception){
                print(e.message)
            }
        }
        return data
    }
}