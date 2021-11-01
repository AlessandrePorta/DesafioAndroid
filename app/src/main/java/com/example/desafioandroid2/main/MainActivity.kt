package com.example.desafioandroid2.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid2.R
import com.example.desafioandroid2.main.model.MainResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        mainViewModel.repos.observe(this) { setupList(it) }
        mainViewModel.fetchRepos()
    }

    private fun setupList(response: MainResponse) {
        val recyclerView = findViewById<RecyclerView>(R.id.rv_item_list)
        recyclerView.adapter = MainAdapter(response.resources, this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }

}