package com.course.ex1.viewmodel

// мостик между UI и domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.domain.model.Company
import com.course.domain.usecase.GetCompaniesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyViewModel @Inject constructor(
    private val getCompaniesUseCase: GetCompaniesUseCase
) : ViewModel() {

    private val _companies = MutableLiveData<List<Company>>()
    val companies: LiveData<List<Company>> = _companies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadCompanies()
    }

    // Запрос данных через UseCase
    private fun loadCompanies() {
        _isLoading.value = true
        viewModelScope.launch {

            try {
                _companies.value = getCompaniesUseCase()
            } catch (e: Exception) {
                Log.e("CompanyViewModel", "Error loading companies", e)
                _companies.value = emptyList() // Ошибка, возвращаем пустой список
            }
            finally {
                _isLoading.value = false
            }

        }
    }
}