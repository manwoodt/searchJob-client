package com.course.ex1.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.course.domain.model.Company
import com.course.ex1.viewmodel.CompanyViewModel

@Composable
fun CompaniesScreen(viewModel: CompanyViewModel, onCompanyClick: (Int) -> Unit) {
    val companies by viewModel.companies.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState(null)

    when {
        isLoading -> LoadingScreen()
        errorMessage != null -> ErrorScreen(errorMessage!!)
        else -> CompaniesList(companies, onCompanyClick)
    }
}


@Composable
fun CompaniesList(companies: List<Company>, onCompanyClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(companies) { company ->
            CompanyItem(company, onCompanyClick)
        }
    }
}

@Composable
fun CompanyItem(company: Company, onCompanyClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp) // Отступ вокруг карточки
            .clickable { onCompanyClick(company.companyId) }
            .background(
                color = Color(0xFFF0F0F0),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp) // Внутренние отступы внутри карточки
    ) {
        Text(
            text = company.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E88E5), // синий
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Сфера деятельности: ${company.fieldOfActivity}",
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Gray
        )
    }
}

