package com.devrachit.manual_di

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.devrachit.manual_di.containers.AppContainer
import com.devrachit.manual_di.containers.UserContainer
import com.devrachit.manual_di.dataSource.UserLocalDataSource
import com.devrachit.manual_di.dataSource.UserRemoteDataSource
import com.devrachit.manual_di.model.UserData
import com.devrachit.manual_di.repository.UserRepository
import com.devrachit.manual_di.services.LoginService
import com.devrachit.manual_di.ui.theme.ManualDITheme
import com.devrachit.manual_di.viewmodel.MainViewModel
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var appContainer: AppContainer
    private lateinit var userData: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        /**Gets [UserRepository] from the instance of AppContainer in Application*/
        val appContainer = (application as App).appContainer
        appContainer.userContainer = UserContainer(appContainer.userRepository)
        mainViewModel = appContainer.userContainer?.mainViewModelFactory?.create() ?: throw IllegalStateException("MainViewModelFactory returned null")
        userData = appContainer.userContainer?.userData ?: throw IllegalStateException("UserData returned null")

        setContent {
            ManualDITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    override fun onDestroy() {
        appContainer.userContainer = null
        super.onDestroy()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ManualDITheme {
        Greeting("Android")
    }
}