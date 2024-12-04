package com.course.ex1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.course.ex1.ui.CompaniesScreen
import com.course.ex1.ui.VacanciesScreen
import com.course.ex1.viewmodel.CompanyViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.course.ex1.ui.CompanyDetailsScreen
import com.course.ex1.ui.VacancyDetailsScreen
import com.course.ex1.viewmodel.CompanyDetailsViewModel
import com.course.ex1.viewmodel.VacancyViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "companies"
) {
    NavHost(
        navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable("companies") {
            val companyViewModel: CompanyViewModel = hiltViewModel()
            CompaniesScreen(
                companyViewModel,
                onCompanyClick = { companyId ->
                    navController.navigate("companies/$companyId")
                }
            )
        }
        composable("companies/{companyId}") { backStackEntry ->
            val companyDetailsViewModel:CompanyDetailsViewModel = hiltViewModel()

            val companyId = backStackEntry.arguments?.getString("companyId")?.toIntOrNull()

            if (companyId != null) {
                CompanyDetailsScreen(navController, companyId)
            }
        }

        composable("vacancies") {
            val vacancyViewModel: VacancyViewModel = hiltViewModel()
            VacanciesScreen(
                vacancyViewModel,
                onVacancyClick = {vacancyId->
                navController.navigate("vacancies/$vacancyId")
            }) }

        composable("vacancies/{vacancyId}") { backStackEntry ->
            val vacancyId = backStackEntry.arguments?.getString("vacancyId")?.toIntOrNull()
            if (vacancyId != null) {
                VacancyDetailsScreen(navController, vacancyId)
            }
        }
    }
}