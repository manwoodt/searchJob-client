package com.course.ex1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.course.domain.model.Vacancy
import com.course.ex1.ui.theme.myBlueColor
import com.course.ex1.ui.theme.myFleshColor
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
        else -> {
            vacancyDetails?.let { details ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    VacancyItemNonClickable(vacancy = details) {
                        val companyId = companies?.find { it.name == details.companyName }?.companyId ?: 1
                        navController.navigate("companies/$companyId")
                    }
                }
            }
        }
    }
}

@Composable
fun VacancyItemNonClickable(vacancy: Vacancy, onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = myFleshColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = vacancy.profession,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = myBlueColor,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        TextInfo(label = "Уровень кандидата:", value = vacancy.level)
        TextInfo(label = "Уровень зарплаты:", value = vacancy.salary)
        TextInfo(label = "Описание:", value = vacancy.description)

        Button(
            onClick = onButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            colors = ButtonDefaults.buttonColors(containerColor =myBlueColor)
        ) {
            Text(
                text = vacancy.companyName,
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun TextInfo(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 20.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontSize = 20.sp
        )
    }
}
