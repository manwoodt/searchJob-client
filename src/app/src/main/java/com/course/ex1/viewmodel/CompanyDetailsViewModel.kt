package com.course.ex1.viewmodel

// мостик между UI и domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.domain.model.CompanyInfo
import com.course.domain.model.Vacancy
import com.course.domain.usecase.GetCompanyDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyDetailsViewModel @Inject constructor(
    private val getCompanyDetailsUseCase: GetCompanyDetailsUseCase
) : ViewModel() {

    private val _companyInfo = MutableLiveData<CompanyInfo>()
    val companyInfo: LiveData<CompanyInfo> = _companyInfo

    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>> get() = _vacancies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    // Запрос данных через UseCase
    fun loadCompanyInfo(companyId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _companyInfo.value = getCompanyDetailsUseCase(companyId)
            } catch (e: Exception) {
                _errorMessage.value =e.message
            }
            finally {
                _isLoading.value = false
            }

        }
    }
}