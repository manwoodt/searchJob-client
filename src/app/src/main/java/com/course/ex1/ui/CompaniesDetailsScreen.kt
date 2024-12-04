package com.course.ex1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.course.domain.model.CompanyDetails
import com.course.domain.model.Vacancy
import com.course.ex1.ui.theme.myBlueColor
import com.course.ex1.ui.theme.myFleshColor
import com.course.ex1.ui.theme.myLightBlueColor
import com.course.ex1.viewmodel.CompanyDetailsViewModel


@Composable
fun CompanyDetailsScreen(
    navController: NavHostController,
    companyId: Int,
    viewModel: CompanyDetailsViewModel = hiltViewModel(),
) {
    val companyDetails by viewModel.companyDetails.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState(null)


    LaunchedEffect(companyId) {
        viewModel.loadCompanyDetails(companyId)
    }

    when {
        isLoading -> LoadingScreen()
        errorMessage != null -> ErrorScreen(errorMessage!!)
        else -> companyDetails?.let { details ->
            CompanyDetailsContent(details, onVacancyClick = { vacancyId ->
                navController.navigate("vacancies/$vacancyId")
            })
        }
    }

}

@Composable
fun CompanyDetailsContent(details: CompanyDetails, onVacancyClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize(),

        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            CompanyHeader(details = details)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Вакансии:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = myBlueColor,
                modifier = Modifier.padding(bottom = 4.dp)
                    .align(Alignment.CenterHorizontally)
            )
            VacancyListFromCompany(
                vacancies = details.vacancies,
                onVacancyClick = onVacancyClick
            )
        }
    }
}

@Composable
fun CompanyHeader(details: CompanyDetails) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = myFleshColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(24.dp)
    ) {
        Text(
            text = details.name,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = myLightBlueColor,
            modifier = Modifier.padding(bottom = 12.dp)
                .align(Alignment.CenterHorizontally)
        )
        TextInfo("Сфера: ",details.fieldOfActivity)
        TextInfo("Контакты: ",details.contacts)
    }
}

@Composable
fun VacancyListFromCompany(vacancies: List<Vacancy>, onVacancyClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        items(vacancies) { vacancy ->
            VacancyNameItem(vacancy, onVacancyClick)
        }
    }
}

@Composable
fun VacancyNameItem(vacancy: Vacancy, onVacancyClick: (Int) -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onVacancyClick(vacancy.vacancyId) }
            .background(
                color = myFleshColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = vacancy.profession,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = myBlueColor,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}
