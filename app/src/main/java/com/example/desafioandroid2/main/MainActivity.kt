package com.example.desafioandroid2.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid2.R
import com.example.desafioandroid2.main.model.MainResponse
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

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
        mainViewModel.fetchRepos()

    }

    private fun setupList(response: MainResponse) {
        val search = findViewById<EditText>(R.id.et_search)
        val rv_item_list = findViewById<RecyclerView>(R.id.rv_item_list)
        val date = findViewById<TextView>(R.id.tv_updateat)

        date.text.run{
            "Criado: ${getDate("yyyy/MM/dd'T'HH/mm/ssZ")}"
        }

        rv_item_list.adapter = MainAdapter(response.resources, this)
        rv_item_list.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var listaAux = mainAdapter.items.filter {
                    it.value!!.toLowerCase().contains(s.toString().toLowerCase())
                    it.resource_id!!.toLowerCase().contains(s.toString().toLowerCase())
                }
                rv_item_list.adapter = MainAdapter(listaAux.toMutableList(), this@MainActivity)
                rv_item_list.adapter?.notifyDataSetChanged()
            }
        })
    }

    fun getDate(dateFormat : String) : String{
        val formatter = SimpleDateFormat(dateFormat)
        val calendar: Calendar = Calendar.getInstance()

        return formatter.format(calendar.getTime())
    }
}