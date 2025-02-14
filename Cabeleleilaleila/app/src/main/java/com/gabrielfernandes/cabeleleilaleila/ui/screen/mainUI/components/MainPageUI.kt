package com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.cabeleleilaleila.R
import com.gabrielfernandes.cabeleleilaleila.models.User
import com.gabrielfernandes.cabeleleilaleila.viewmodels.MainPageClientViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainPageUI() {


    Scaffold(
        containerColor = colorResource(id = R.color.light_pink)
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            UserPageUI()
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun Preview() {
    MainPageUI()
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserPageUI() {
    val viewModel: MainPageClientViewModel = koinViewModel()
    val listService by viewModel.listService.collectAsState()
    val total by viewModel.total.collectAsState()
    val user by viewModel.userPreferences.userFlow.collectAsState(initial = User(0, "", "", ""))
    val clean by viewModel.clean.collectAsState()

    var selectedDate by remember {
        mutableStateOf("")
    }
    var qtd by remember {
        mutableIntStateOf(1)
    }

    LaunchedEffect(clean) {
        if (clean) {
            qtd = 1
            selectedDate = ""
            viewModel.finishClean()
        }
    }

    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            item {
                DatePickerMainPage {
                    selectedDate = it
                    viewModel.setSelectedDate(it)
                }
            }

            if (selectedDate.isNotEmpty()) {
                item {
                    TextFieldPadrao(
                        text = selectedDate,
                        onValueChance = {},
                        label = "Data selecionada",
                        readOnly = true
                    )
                }
                item {
                    CustomTimePickerDialog(
                        onTimeSelected = {hour, minute -> viewModel.setHour(hour, minute)}
                    )
                }
                items(qtd) { index ->
                    DefaultComboBox(
                        list = listService,
                        onMenosCLick = {
                            if (qtd > 1) {
                                viewModel.eraseService(index)
                                qtd -= 1
                            } else qtd = 1
                        },
                        onMaisClick = { qtd += 1 },
                        onItemClick = { service ->
                            viewModel.addService(service, index)
                        },
                        canAdd = index == qtd - 1
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
        ) {
            Text(
                text = "Total: ",
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            )
            Text(
                text = "R$ ${String.format("%.2f", total)}",
                modifier = Modifier.padding(10.dp)
            )
            Button(
                onClick = { viewModel.finalize(user) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = "Finalizar",
                    color = Color.Blue
                )
            }
        }
    }

}

@Composable
private fun TextFieldPadrao(
    text: String,
    onValueChance: (String) -> Unit,
    label: String,
    readOnly: Boolean
) {
    TextField(
        value = text,
        onValueChange = {
            onValueChance(it)
        },
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .shadow(3.dp, RoundedCornerShape(20.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(20.dp)),
        readOnly = readOnly,
        label = { Text(text = label) },
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

