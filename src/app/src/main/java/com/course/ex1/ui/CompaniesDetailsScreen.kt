package com.course.ex1.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.course.ex1.viewmodel.CompanyDetailsViewModel


@Composable
fun CompanyDetailsScreen(viewModel: CompanyDetailsViewModel, companyId: Int) {
    val companyDetails by viewModel.companyInfo.observeAsState()
    val vacancies by viewModel.vacancies.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState(null)

    LaunchedEffect(companyId) {
        viewModel.loadCompanyInfo(companyId)
    }

    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else if (errorMessage != null) {
        Log.e("Error", errorMessage!!)
        Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_SHORT).show()
    } else {
        companyDetails?.let { details ->
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(text = details.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = details.fieldOfActivity, style = MaterialTheme.typography.bodySmall)
                Text(text = details.contacts, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Вакансии:", style = MaterialTheme.typography.bodyLarge)
                vacancies.forEach { vacancy ->
                    Text(text = vacancy.description)
                }
            }
        }
    }
}
