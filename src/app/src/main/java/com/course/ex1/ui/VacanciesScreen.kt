package com.course.ex1.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.course.domain.model.Company
import com.course.domain.model.Vacancy
import com.course.ex1.viewmodel.VacancyViewModel


@Composable
fun VacanciesScreen(viewModel: VacancyViewModel, onVacancyClick: (Int) -> Unit) {
    val vacancies by viewModel.vacancies.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val errorMessage by viewModel.errorMessage.observeAsState(null)

    when {
        isLoading -> LoadingScreen()
        errorMessage != null -> ErrorScreen(errorMessage!!)
        else -> VacanciesList(vacancies, onVacancyClick)

    }
}


// Список компаний
@Composable
fun VacanciesList(vacancies: List<Vacancy>, onVacancyClick: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(vacancies) { vacancy ->
            VacancyItem(vacancy, onVacancyClick)
        }
    }
}

@Composable
fun VacancyItem(vacancy: Vacancy, onVacancyClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Название - ${vacancy.profession}",
            modifier = Modifier.clickable { onVacancyClick(vacancy.vacancyId) }
        )
        Text(
            text = "Уровень кандидата - ${vacancy.level}"
        )
        Text(
            text = "Уровень зарплаты - ${vacancy.salary}"
        )
        Text(
            text = "Описание - ${vacancy.description}"
        )
        Text(
            text = "компания - ${vacancy.companyName}"
        )

    }

}

// represent vacancy title, candidate level, salary level, and company name