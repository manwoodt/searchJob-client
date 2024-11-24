package com.course.ex1.ui


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.course.domain.model.Company
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.course.ex1.viewmodel.CompanyViewModel

//@Composable
//fun CompaniesScreen(viewModel: CompaniesViewModel) {
//    val companies by viewModel.companies
//    val isLoading by viewModel.isLoading
//
//    if (isLoading) {
//        CircularProgressIndicator()
//    } else {
//        LazyColumn {
//            items(companies) { company ->
//                Text("${company.name} (${company.activityField})")
//            }
//        }
//    }
//}

@Composable
fun CompaniesScreen(viewModel: CompanyViewModel) {
    // Подписываемся на поток данных из ViewModel
    val companies by viewModel.companies.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)

    if (isLoading)
        CircularProgressIndicator()
    else {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            items(companies) { company ->
                CompanyItem(company)
            }
        }
    }
}

@Composable
fun CompanyItem(company: Company) {
    Text(text = "${company.name} - ${company.fieldOfActivity}", modifier = Modifier.padding(8.dp))
}
