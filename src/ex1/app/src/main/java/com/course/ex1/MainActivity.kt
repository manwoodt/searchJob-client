package com.course.ex1
/*
com.example.jobapp
│
├── data
│   ├── model         // Модели данных (DTO)
│   ├── network       // API-интерфейсы и Retrofit
│   └── repository    // Реализация репозиториев
│
├── domain
│   ├── model         // Бизнес-модели
│   └── repository    // Интерфейсы репозиториев
│
├── presentation
│   ├── ui            // Компоненты интерфейса Compose
│   ├── viewmodel     // ViewModel
│   └── navigation    // Навигация
│
└── di                // Dagger Hilt модули
*/

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.course.di.DependencyProvider
import com.course.ex1.ui.CompaniesScreen
import com.course.ex1.ui.theme.Ex1Theme
import com.course.ex1.viewmodel.CompanyViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: CompanyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Вручную создаем ViewModel с зависимостью
        viewModel = CompanyViewModel(DependencyProvider.getCompanyUseCase)

        setContent {
            Ex1Theme {
                CompaniesScreen(viewModel)
            }
        }
    }
}



/*
        setContent {
            Ex1Theme {
                val viewModel: CompaniesViewModel = viewModel()
                val companies = viewModel.companies.observeAsState(emptyList())
                CompaniesScreen(companies = companies.value)
            }
        }
 */

 /*


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.course.ex1.viewmodel.CompanyViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}
@Preview
@Composable
fun MyApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(navController, startDestination = "companies", Modifier.padding(innerPadding)) {
            composable("companies") { CompaniesScreen(viewModel()) }
            composable("vacancies") { VacanciesScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = null) },
            label = { Text("Компании") },
            selected = false,
            onClick = { navController.navigate("companies") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Call, contentDescription = null) },
            label = { Text("Вакансии") },
            selected = false,
            onClick = { navController.navigate("vacancies") }
        )
    }
}

@Composable
fun CompaniesScreen(viewModel: CompanyViewModel = viewModel()) {
    val companies by viewModel.companies.observeAsState(emptyList())

    LazyColumn {
        items(companies) { company ->
            CompanyItem(company)
        }
    }
}

@Composable
fun VacanciesScreen() {

}

@Composable
fun CompanyItem(company: Company) {
    Text(text = "${company.name} - ${company.fieldOfActivity}")
}

*/