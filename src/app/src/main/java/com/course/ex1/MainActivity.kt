package com.course.ex1
/*
структура проекта
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
как работает проект
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
import com.course.ex1.ui.MyApp
import com.course.ex1.ui.theme.Ex1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Ex1Theme {
                MyApp()
            }
        }


    }
}

