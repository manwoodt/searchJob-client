package com.course.ex1.ui


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.course.domain.model.Company
import com.course.ex1.viewmodel.CompanyViewModel

@Composable
fun CompaniesScreen(viewModel: CompanyViewModel, onCompanyClick: (Int)->Unit) {
    val companies by viewModel.companies.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState(null)

when {
    isLoading -> LoadingScreen()
    errorMessage != null -> ErrorScreen(errorMessage!!)
    else -> CompaniesList(companies, onCompanyClick)
}
}

// Экран загрузки
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

// Экран ошибки
@Composable
fun ErrorScreen(errorMessage: String) {
    Toast.makeText(LocalContext.current, errorMessage, Toast.LENGTH_LONG).show()
}

// Список компаний
@Composable
fun CompaniesList(companies: List<Company>, onCompanyClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(companies) { company ->
            CompanyItem(company,onCompanyClick)
        }
    }
}
@Composable
fun CompanyItem(company: Company, onCompanyClick: (Int) -> Unit) {
    Text(
        text = "${company.name} - ${company.fieldOfActivity}",
        modifier = Modifier
            .padding(8.dp)
            .clickable { onCompanyClick(company.id) }
    )

}

