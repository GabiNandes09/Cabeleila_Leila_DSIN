package com.gabrielfernandes.cabeleleilaleila.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.gabrielfernandes.cabeleleilaleila.R

@Composable
fun LoginUi() {
    Scaffold(
        containerColor = colorResource(id = R.color.light_pink)
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
            TextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Email:",
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
            TextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Senha:",
                        color = Color.Black
                    )
                },
                modifier = Modifier
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
                    text = "Entrar",
                    color = Color.White
                )
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .padding(vertical = 20.dp)
            ) {
                Text(
                    text = "Não possui conta? \n Clique para cadastrar",
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    LoginUi()
}