package com.example.desafioandroid2.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid2.R
import com.example.desafioandroid2.main.model.ListaItems
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        mainViewModel.repos.observe(this) { setupList(it) }
        mainViewModel.repos.observe(this) { searchValue(it)}
        mainViewModel.fetchRepos()

    }

    private fun setupList(response: ListaItems) {
        val rv_item_list = findViewById<RecyclerView>(R.id.rv_item_list)


        rv_item_list.adapter = MainAdapter(response, this)
        rv_item_list.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }

    fun searchValue(response: ListaItems){
        val rv_item_list = findViewById<RecyclerView>(R.id.rv_item_list)
        val search = findViewById<EditText>(R.id.et_search)

        mainAdapter =
            MainAdapter(
                response,
                this
            )
        rv_item_list.apply {
            setHasFixedSize(true)
            adapter = mainAdapter
        }

        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var listaAux = mainAdapter.items.filter {
                    it.resource.value!!.lowercase().contains(s.toString().lowercase())
                }
                rv_item_list.adapter = MainAdapter(listaAux.toMutableList(), this@MainActivity)
                rv_item_list.adapter?.notifyDataSetChanged()
            }
        })
    }
}