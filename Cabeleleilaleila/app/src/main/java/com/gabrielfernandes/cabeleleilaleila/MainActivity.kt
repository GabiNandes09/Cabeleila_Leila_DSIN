package com.gabrielfernandes.cabeleleilaleila

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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.cabeleleilaleila.ui.screen.CadastroUI
import com.gabrielfernandes.cabeleleilaleila.ui.screen.LoginUi
import com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI.MainUi
import com.gabrielfernandes.cabeleleilaleila.ui.theme.CabeleleilaleilaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CabeleleilaleilaTheme {
               val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login"){
                    composable("login"){
                        LoginUi(navController)
                    }
                    composable("cadastro"){
                        CadastroUI(navController)
                    }
                    composable("main"){
                        MainUi(navController = navController)
                    }
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
    CabeleleilaleilaTheme {
        Greeting("Android")
    }
}