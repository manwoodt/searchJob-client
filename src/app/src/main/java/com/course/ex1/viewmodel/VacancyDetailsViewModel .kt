package com.course.ex1.viewmodel

// мостик между UI и domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.domain.model.Company
import com.course.domain.model.Vacancy
import com.course.domain.model.VacancyDetails
import com.course.domain.usecase.GetCompaniesUseCase
import com.course.domain.usecase.GetVacanciesUseCase
import com.course.domain.usecase.GetVacancyDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacancyDetailsViewModel  @Inject constructor(
    private val getVacancyDetailsUseCase: GetVacancyDetailsUseCase,
    private val getCompaniesUseCase: GetCompaniesUseCase
) : ViewModel() {

    private val _vacancyDetails = MutableLiveData<Vacancy>()
    val vacancyDetails: LiveData<Vacancy> = _vacancyDetails

    private val _companies = MutableLiveData<List<Company>>()
    val companies: LiveData<List<Company>> = _companies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage


    // Запрос данных через UseCase
    fun loadVacancyDetails(vacancyId:Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _vacancyDetails.value = getVacancyDetailsUseCase(vacancyId)
                _companies.value = getCompaniesUseCase()
            } catch (e: Exception) {
                _errorMessage.value = "Error loading vacancy details ${e.message}"
            }
            finally {
                _isLoading.value = false
            }

        }
    }
}