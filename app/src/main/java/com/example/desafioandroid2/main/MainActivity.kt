package com.example.desafioandroid2.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid2.R
import com.example.desafioandroid2.main.model.ListaItems
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        showLoading(true)
        mainViewModel.repos.observe(this) { setupList(it) }
        mainViewModel.repos.observe(this) { searchValue(it)}
        //mainViewModel.repos.observe(this) { filterList(it) }
        mainViewModel.fetchRepos()

    }

    private fun setupList(response: ListaItems) {
        showLoading(false)
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

//    fun filterList(listaItems : ListaItems){
//        lateinit var spinner = findViewById<Spinner>(R.id.sp_list)
//        val listFilter = findViewById<TextView>(R.id.tv_picked)
//        val listaAux : MutableList<String> = mutableListOf()
//
//        listaItems.forEach{
//            listaItems.first().resource.language_id.forEach {
//                if(!listaItems.first().resource.language_id.contains(it)){
//                    listaAux.add(it.toString())
//                }
//            }
//        }
//        val adapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
//            this,
//            R.array.teste,
//            android.R.layout.simple_spinner_item
//        )
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        spinner.adapter = adapter
//
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View,
//                position: Int,
//                id: Long
//            ) {
//                listFilter.text = parent?.getItemAtPosition(position).toString()
//                Toast.makeText(parent?.context, listFilter.toString(),  Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//    }

    fun showLoading(isLoaded : Boolean){
        val loading = findViewById<ProgressBar>(R.id.pb_loading)
        loading.isVisible = isLoaded
    }
}