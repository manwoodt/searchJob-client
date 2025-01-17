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
import androidx.compose.material3.Text
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
import com.course.domain.model.Vacancy
import com.course.ex1.ui.theme.myBlueColor
import com.course.ex1.ui.theme.myFleshColor
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
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onVacancyClick(vacancy.vacancyId) }
            .background(
                color = myFleshColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = vacancy.profession,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = myBlueColor,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Уровень кандидата: ${vacancy.level}",
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Уровень зарплаты: ${vacancy.salary}",
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Компания: ${vacancy.companyName}",
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            color = myBlueColor,
        )
    }
}



