package com.course.ex1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CompanyViewModel: ViewModel() {
    private val repository = CompanyRepository()

    private val _companies = MutableLiveData<List<Company>>()
    val companies :LiveData<List<Company>> get() = _companies

    init {
        viewModelScope.launch {
            _companies.value = repository.getCompany()
        }
    }
}