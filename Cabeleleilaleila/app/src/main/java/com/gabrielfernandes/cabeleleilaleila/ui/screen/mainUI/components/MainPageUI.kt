package com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.cabeleleilaleila.R
import com.gabrielfernandes.cabeleleilaleila.models.Service
import com.gabrielfernandes.cabeleleilaleila.viewmodels.MainPageViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import java.time.Instant
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainPageUI() {
    val viewModel: MainPageViewModel = koinViewModel()
    val listService by viewModel.listService.collectAsState()

    Scaffold(
        containerColor = colorResource(id = R.color.light_pink)
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            UserPageUI(
                listService
            )
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPageUI(
    listService: List<Service>
) {
    var selectedDate by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    var servico: String? by remember {
        mutableStateOf(null)
    }
    var expandedService by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            DatePickerMainPage {
                selectedDate = it
            }
        }

        if (selectedDate.isNotEmpty()) {
            item {
                TextFieldPadrao(
                    text = selectedDate,
                    onValueChance = {},
                    label = "DataSelecionada",
                    readOnly = true
                )
            }
            item {
                DefaultComboBox(list = listService)
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