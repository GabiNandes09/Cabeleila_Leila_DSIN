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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.gabrielfernandes.cabeleleilaleila.viewmodels.CadastroViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CadastroUI(
    navController: NavController
) {
    val viewModel: CadastroViewModel = koinViewModel()

    val name by viewModel.name.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmation by viewModel.confirmation.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val passwordDoenstMatch by viewModel.passwordDoenstMatch.collectAsState()
    val complete by viewModel.complete.collectAsState()

    if (hasError || passwordDoenstMatch){
        AlertDialog(
            onDismissRequest = { viewModel.onOkayClick() },
            confirmButton = {
                Button(onClick = { viewModel.onOkayClick() }) {
                    Text(text = "Entendido")
                }
            },
            title = {
                Text(text = if (hasError) "Algo deu errado!" else if (passwordDoenstMatch) "As senhas não são iguais" else "")
            },
            text = {
                Text(text = "Por favor, corrija e tente novamente!")
            }
        )
    }

    LaunchedEffect(complete) {
        if (complete) navController.navigate("main")
    }

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
                value = name,
                onValueChange = { viewModel.setName(it) },
                label = "Nome:"
            )
            DefaultTextField(
                value = email,
                onValueChange = { viewModel.setEmail(it) },
                label = "Email:"
            )
            DefaultTextField(
                value = password,
                onValueChange = { viewModel.setPassword(it) },
                label = "Senha:"
            )
            DefaultTextField(
                value = confirmation,
                onValueChange = { viewModel.setConfirmation(it) },
                label = "Confirme a senha"
            )

            Button(
                onClick = { viewModel.cadastrar() },
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