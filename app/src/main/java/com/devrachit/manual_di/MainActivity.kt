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
import com.devrachit.manual_di.dataSource.UserLocalDataSource
import com.devrachit.manual_di.dataSource.UserRemoteDataSource
import com.devrachit.manual_di.repository.UserRepository
import com.devrachit.manual_di.services.LoginService
import com.devrachit.manual_di.ui.theme.ManualDITheme
import com.devrachit.manual_di.viewmodel.MainViewModel
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        /**
         * In order to satisfy the dependencies of [MainViewModel], you have to also
         * satisfy the dependencies of all of its dependencies recursively.
         * First, create retrofit which is the dependency of UserRemoteDataSource
         */
        val retrofit = Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(LoginService::class.java)

        /**Then, satisfy the dependencies of [UserRepository]**/
        val remoteDataSource = UserRemoteDataSource(retrofit)
        val localDataSource = UserLocalDataSource()

        /**
         * Now you can create an instance of [UserRepository] that [MainViewModel] needs
         */
        val userRepository = UserRepository(localDataSource, remoteDataSource)

        /**
         * Lastly, create an instance of [MainViewModel] with [UserRepository] as
         */

        mainViewModel = MainViewModel(userRepository)
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