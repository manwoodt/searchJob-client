import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.course.ex1.Company
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.course.ex1.CompanyViewModel

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
fun CompaniesScreen(viewModel: CompanyViewModel= viewModel()) {
    val companies by viewModel.companies.observeAsState(emptyList())

    LazyColumn {
        items(companies) { company ->
            CompanyItem(company)
        }
    }
}

@Composable
fun VacanciesScreen() {
    // Здесь будет ваша логика для вкладки "Вакансии"
}

@Composable
fun CompanyItem(company: Company) {
    Text(text = "${company.name} - ${company.fieldOfActivity}")
}