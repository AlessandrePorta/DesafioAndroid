package com.example.desafioandroid2.main

import androidx.lifecycle.*
import com.example.desafioandroid2.network.Repository
import kotlinx.coroutines.flow.collect

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _repos = MutableLiveData<Unit>()

    val repos = _repos.switchMap {
        liveData {
            repository.getDetails().collect {
                emit(it)
            }
        }
    }

    fun fetchRepos() {
        _repos.postValue(Unit)
    }

}