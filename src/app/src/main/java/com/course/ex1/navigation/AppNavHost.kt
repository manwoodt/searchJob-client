package com.course.ex1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.course.ex1.ui.CompaniesScreen
import com.course.ex1.ui.VacanciesScreen
import com.course.ex1.viewmodel.CompanyViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination:String = "companies",
    modifier: Modifier = Modifier
){
    NavHost(
        navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        composable("companies") {
            val companyViewModel: CompanyViewModel = hiltViewModel()
            CompaniesScreen(companyViewModel) }
        composable("vacancies") { VacanciesScreen() }
    }
}