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

/*
В MainActivity:
1.Создаётся объект CompaniesViewModel, которому передаётся GetCompanyUseCase.
Вызывается метод setContent, инициализирующий экран CompaniesScreen.

2.В CompaniesViewModel:
В методе init вызывается loadCompanies(), который запускает корутину.
В корутине выполняется метод execute() из GetCompanyUseCase.

3.В GetCompanyUseCase:
Вызывается метод getCompanies() из CompanyRepository.
CompanyRepositoryImpl обращается к ApiService, который отправляет HTTP-запрос на сервер.
Полученные данные преобразуются из CompanyDto в Company.
Данные возвращаются в CompaniesViewModel.

4.В CompaniesViewModel:
Обновляется LiveData, что вызывает перерисовку CompaniesScreen.

5.В CompaniesScreen:
Список компаний отображается в виде элементов LazyColumn.
 */

/*
по слоям

1 Data (Слой данных):
ApiService отправляет HTTP-запрос к вашему серверу (через Retrofit).
Сервер возвращает список объектов CompanyDto.
CompanyRepositoryImpl преобразует CompanyDto в бизнес-модель Company

2 Domain (Доменный слой):
Презентационный слой вызывает метод execute() в GetCompanyUseCase.
GetCompanyUseCase вызывает метод getCompanies() из репозитория.
Данные возвращаются в виде бизнес-моделей Company.

3 Presentation (Презентационный слой):
MainActivity запускает CompaniesScreen, передавая в него ViewModel.
CompaniesViewModel:
Вызывает GetCompanyUseCase для получения списка компаний.
Сохраняет данные в LiveData, чтобы UI мог автоматически обновляться.
CompaniesScreen подписывается на LiveData через observeAsState
и отображает список компаний с помощью LazyColumn.
 */

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.course.di.DependencyProvider
import com.course.ex1.ui.CompaniesScreen
import com.course.ex1.ui.theme.Ex1Theme
import com.course.ex1.viewmodel.CompanyViewModel

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