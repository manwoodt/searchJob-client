package com.course.ex1.viewmodel

// мостик между UI и domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.domain.model.Company
import com.course.domain.model.Vacancy
import com.course.domain.usecase.GetCompaniesUseCase
import com.course.domain.usecase.GetVacanciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacancyViewModel @Inject constructor(
    private val getVacanciesUseCase: GetVacanciesUseCase
) : ViewModel() {

    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val companies: LiveData<List<Vacancy>> = _vacancies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        loadVacancies()
    }

    // Запрос данных через UseCase
    private fun loadVacancies() {
        _isLoading.value = true
        viewModelScope.launch {

            try {
                _vacancies.value = getVacanciesUseCase()
                _errorMessage.value = null // Сбрасываем сообщение об ошибке
            } catch (e: Exception) {
                _errorMessage.value = "Error loading vacancies ${e.message}"
                Log.e("VacancyViewModel", "Error loading vacancies", e)
                _vacancies.value = emptyList() // Ошибка, возвращаем пустой список
            }
            finally {
                _isLoading.value = false
            }

        }
    }
}