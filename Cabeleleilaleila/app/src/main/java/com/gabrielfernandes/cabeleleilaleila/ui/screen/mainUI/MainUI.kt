package com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.cabeleleilaleila.R
import com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI.components.HeadUI
import com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI.components.MainBottonBar
import com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI.components.MainPageUI

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainUi(
    navController: NavController
) {
    Scaffold(
        containerColor = colorResource(id = R.color.light_pink),
        topBar = { HeadUI() },
        bottomBar = { MainBottonBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize()
        ) {
            MainPageUI()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun Preview() {
    val navController = rememberNavController()
    MainUi(navController = navController)
}