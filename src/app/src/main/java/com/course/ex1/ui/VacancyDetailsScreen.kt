package com.course.ex1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.course.ex1.viewmodel.VacancyDetailsViewModel

@Composable
fun VacancyDetailsScreen(
    navController: NavHostController,
    vacancyId: Int,
    viewModel: VacancyDetailsViewModel = hiltViewModel(),
) {
    val vacancyDetails by viewModel.vacancyDetails.observeAsState()
    val companies by viewModel.companies.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState(null)

    LaunchedEffect(vacancyId) {
        viewModel.loadVacancyDetails(vacancyId)
    }

    when {
        isLoading -> LoadingScreen()
        errorMessage != null -> ErrorScreen(errorMessage!!)
        else ->
            vacancyDetails?.let { details ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Должность: ${details.profession}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text("Описание: ${details.description}")
                    Text("Зарплата: ${details.salary}")
                    Text("Уровень: ${details.level}")
                    Spacer(modifier = Modifier.height(16.dp))
                    var companyId = 1
                    for (company in companies!!) {
                        if (company.name == details.companyName) {
                            companyId = company.companyId
                        }
                    }
                    Button(onClick = {
                        navController.navigate(
                            "companies/${companyId}"
                        )
                    }) {
                        Text("Перейти в компанию: ${details.companyName}")
                    }
                }
            }
    }
}
