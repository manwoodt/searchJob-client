package com.course.ex1.navigation

import android.util.Log
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
    navController: NavHostController,
    startDestination: String = "companies",
    modifier: Modifier = Modifier,
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
                   // Log.d("Navigation", "Navigating to companyInfo/$companyId")
                    navController.navigate("companyInfo/$companyId")
                }
            )
        }
        composable("companyInfo/{companyId}") { backStackEntry ->
            val companyDetailsViewModel:CompanyDetailsViewModel = hiltViewModel()

            val companyId = backStackEntry.arguments?.getString("companyId")?.toIntOrNull()

            if (companyId != null) {
               // Log.d("Navigation", "Extracted companyId: $companyId")
                CompanyDetailsScrees, companyId)
            }
        }

        composable("vacancies") {
            val vacancyViewModel: VacancyViewModel = hiltViewModel()
            VacanciesScreen(vacancyViewModel,{}) }

        composable("vacancyDetails/{vacancyId}") { backStackEntry ->
            val vacancyId = backStackEntry.arguments?.getString("vacancyId")?.toIntOrNull()
            if (vacancyId != null) {
                VacancyDetailsScreen(navController, vacancyId)
            }
        }
    }
}