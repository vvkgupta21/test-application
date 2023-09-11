package com.example.testapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.testapplication.adapter.PostDataAdapter
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.network.TestApi
import com.example.testapplication.network.apiUtils.StateData
import com.example.testapplication.repository.Repository
import com.example.testapplication.viewModel.PostViewModel

const val TAG = "Test"

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var adapter :PostDataAdapter
    lateinit var viewModel: PostViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val repository = Repository(TestApi)
        viewModel = ViewModelProvider(this, PostViewModelFactory(repository))[PostViewModel::class.java]
        initAdapter()
        getPostData()
    }

    private fun initAdapter(){
        adapter = PostDataAdapter(arrayListOf())
        dataBinding.rv.adapter = adapter
    }

    private fun getPostData(){
        viewModel.getPostData().observe(this){
            it?.let { stateData ->
                when(stateData.status){
                    StateData.DataStatus.SUCCESS -> {
                        val data = stateData.data
                        if (data != null) {
                            adapter.initList(data)
                        }
                    }
                    StateData.DataStatus.LOADING -> {}
                    StateData.DataStatus.ERROR -> {}
                    else -> {}
                }
            }
        }
    }
}