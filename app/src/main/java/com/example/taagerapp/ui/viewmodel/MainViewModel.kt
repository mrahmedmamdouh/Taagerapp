package com.example.taagerapp.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taagerapp.di.IoDispatcher
import com.example.taagerapp.model.Product
import com.example.taagerapp.model.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import java.sql.Date
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val job = SupervisorJob()
    val repositoriesLiveData = MutableLiveData<List<Product>>()
    private val ioScope = CoroutineScope(ioDispatcher + job)


    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val data = ioScope.async {
                    return@async mainRepository.getProducts()
                }.await()
                repositoriesLiveData.value = data
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun convertToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        return format.format(date)
    }


    override fun onCleared() {
        super.onCleared()
        this.job.cancel()
    }
}