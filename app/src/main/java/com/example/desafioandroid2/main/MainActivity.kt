package com.example.desafioandroid2.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroid2.R
import com.example.desafioandroid2.main.model.Item
import com.example.desafioandroid2.main.model.ListaItems
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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

        showLoading(true)
        mainViewModel.repos.observe(this) { setupList(it) }
        mainViewModel.repos.observe(this) { searchValue() }
        mainViewModel.fetchRepos()

    }

    private fun setupList(response: ListaItems) {

        showLoading(false)
        rvDivider()
        initAdapter(response)
        filterLanguageList(response)
        filterModuleList(response)
        cleanFilters(response)
    }

    private fun initAdapter(response: MutableList<Item>) {
        val rv_item_list = findViewById<RecyclerView>(R.id.rv_item_list)

        mainAdapter = MainAdapter(
            response,
            this
        )

        rv_item_list.adapter = mainAdapter
    }

    private fun searchValue() {
        showLoading(false)
        val rv_item_list = findViewById<RecyclerView>(R.id.rv_item_list)
        val search = findViewById<EditText>(R.id.et_search)

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

    private fun filterLanguageList(response: ListaItems) {
        showLoading(false)
        val rv_item_list = findViewById<RecyclerView>(R.id.rv_item_list)
        val actv_language = findViewById<AutoCompleteTextView>(R.id.actv_language)

        filterLanguages(response)

        actv_language.setOnItemClickListener { adapterView, view, i, l ->
            clearSearchText()

            val listaAux = mainAdapter.items.filter {
                it.resource.language_id.contains(actv_language.text)
            }.toMutableList()

            rv_item_list.adapter = MainAdapter(listaAux, this@MainActivity)
            rv_item_list.adapter?.notifyDataSetChanged()

        }
    }

    private fun filterLanguages(response: ListaItems) {
        val actv = findViewById<AutoCompleteTextView>(R.id.actv_language)
        val lista: ArrayList<String> = arrayListOf()

        response.forEach {
            if (!lista.toString().contains(it.resource.language_id)) {
                lista.add(it.resource.language_id)
            }
        }

        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, lista)
        actv.setAdapter(adapter)
    }

    private fun filterModuleList(response: ListaItems) {
        showLoading(false)
        val rv_item_list = findViewById<RecyclerView>(R.id.rv_item_list)
        val actv_module = findViewById<AutoCompleteTextView>(R.id.actv_module)

        filterModules(response)

        actv_module.setOnItemClickListener { adapterView, view, i, l ->
            clearSearchText()

            var listaAux = mainAdapter.items.filter {
                it.resource.module_id.contains(actv_module.text)
            }.toMutableList()
            rv_item_list.adapter = MainAdapter(listaAux, this@MainActivity)
            rv_item_list.adapter?.notifyDataSetChanged()
        }
    }

    private fun filterModules(response: ListaItems) {
        val actv = findViewById<AutoCompleteTextView>(R.id.actv_module)
        val lista: ArrayList<String> = arrayListOf()

        response.forEach {
            if (!lista.toString().contains(it.resource.module_id)) {
                lista.add(it.resource.module_id)
            }
        }

        val adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, lista)
        actv.setAdapter(adapter)
    }

    private fun cleanFilters(response: ListaItems) {
        val rv_item_list = findViewById<RecyclerView>(R.id.rv_item_list)
        val cleanFilter = findViewById<TextView>(R.id.tv_clean_filters)
        val til_filter_language = findViewById<TextInputLayout>(R.id.til_filters_language)
        val til_filter_module = findViewById<TextInputLayout>(R.id.til_filters_module)

        cleanFilter.setOnClickListener {
            rv_item_list.adapter = MainAdapter(response, this@MainActivity)
            rv_item_list.adapter?.notifyDataSetChanged()

            til_filter_language.editText?.setText(R.string.languages)
            til_filter_module.editText?.setText(R.string.modules)

            filterLanguageList(response)
            filterModuleList(response)
        }
    }

    private fun clearSearchText() {
        val search = findViewById<EditText>(R.id.et_search)
        search.setText("")
    }

    private fun rvDivider() {
        val rv_item_list = findViewById<RecyclerView>(R.id.rv_item_list)
        rv_item_list.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
    }

    private fun showLoading(isLoaded: Boolean) {
        val loading = findViewById<ProgressBar>(R.id.pb_loading)
        val mainActivity = findViewById<ConstraintLayout>(R.id.main_activity2)
        mainActivity.isVisible = !isLoaded
        loading.isVisible = isLoaded
    }

}