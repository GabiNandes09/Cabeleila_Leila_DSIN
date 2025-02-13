package com.gabrielfernandes.cabeleleilaleila.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielfernandes.cabeleleilaleila.R

@Composable
fun CadastroUI(
    navController: NavController
) {
    Scaffold(
        containerColor = colorResource(id = R.color.light_pink),
        topBar = {
            Row {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(10.dp)
                    ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(20.dp)
                    .size(200.dp)
            )

            DefaultTextField(
                value = "",
                onValueChange = {},
                label = "Nome:"
            )
            DefaultTextField(
                value = "",
                onValueChange = {},
                label = "Email:"
            )
            DefaultTextField(
                value = "",
                onValueChange = {},
                label = "Senha:"
            )
            DefaultTextField(
                value = "",
                onValueChange = {},
                label = "Confirme a senha"
            )

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.pink)
                ),
                modifier = Modifier
                    .width(150.dp)
                    .padding(vertical = 20.dp)
                    .shadow(5.dp, RoundedCornerShape(20.dp))
            ) {
                Text(
                    text = "Cadastrar",
                    color = Color.White
                )
            }
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.light_pink)
                ),
                modifier = Modifier
                    .width(150.dp)
                    .shadow(5.dp, RoundedCornerShape(20.dp))
            ) {
                Text(
                    text = "Cancelar",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            Text(
                text = label,
                color = Color.Black
            )
        },
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(20.dp)),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.LightGray,
            focusedContainerColor = Color.White,
            errorContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )
}

@Preview
@Composable
private fun Preview() {
    val navController = rememberNavController()
    CadastroUI(navController)
}