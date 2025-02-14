package com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.flow.collectLatest
import java.time.Instant
import java.time.ZoneOffset

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserPageUI() {
    val datePickerState = rememberDatePickerState()
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
    val listService by remember {
        mutableStateOf(listOf("OP1", "OP2", "OP3"))
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        if (!expanded){
            item {
                TextField(
                    value = "Começar agendamento",
                    onValueChange = {},
                    trailingIcon = {
                        Icon(
                            imageVector = if (expanded)
                                Icons.Default.KeyboardArrowDown
                            else
                                Icons.Default.KeyboardArrowUp,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .shadow(3.dp, RoundedCornerShape(20.dp))
                        .border(2.dp, Color.Black, RoundedCornerShape(20.dp)),
                    readOnly = true,
                    interactionSource = remember {
                        MutableInteractionSource()
                    }.also {
                        LaunchedEffect(key1 = it) {
                            it.interactions.collectLatest { interaction ->
                                if (interaction is PressInteraction.Release) {
                                    expanded = !expanded
                                }
                            }
                        }
                    }
                )
            }
        }
        item {
            AnimatedVisibility(visible = expanded) {
                Column {
                    DatePicker(
                        state = datePickerState,
                        headline = { Text(text = "Selecione: ") }
                    )
                    Button(onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val localDateTime = Instant.ofEpochMilli(millis)
                                .atZone(ZoneOffset.UTC)
                                .toLocalDate()
                            selectedDate = localDateTime.toString()
                        }
                        expanded = false
                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.pink)
                        )
                    ){
                        Text(
                            text = "Selecionar",
                            color = Color.White
                        )
                    }
                }
            }
        }
        if (selectedDate.isNotEmpty()){
            item {
                TextField(
                    value = selectedDate,
                    onValueChange = {},
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .shadow(3.dp, RoundedCornerShape(20.dp))
                        .border(2.dp, Color.Black, RoundedCornerShape(20.dp)),
                    readOnly = true,
                    label = { Text(text = "Data selecionada: ")}
                )
            }
            item {
                Column {
                    TextField(
                        value = servico ?: "",
                        onValueChange = {},
                        trailingIcon = {
                            Icon(
                                imageVector = if (expandedService)
                                    Icons.Default.KeyboardArrowDown
                                else
                                    Icons.Default.KeyboardArrowUp,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .shadow(3.dp, RoundedCornerShape(20.dp))
                            .border(2.dp, Color.Black, RoundedCornerShape(20.dp)),
                        readOnly = true,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                            .also {
                                LaunchedEffect(key1 = it) {
                                    it.interactions.collectLatest { interaction ->
                                        if (interaction is PressInteraction.Release) {
                                            expandedService = !expandedService
                                        }
                                    }
                                }
                            }
                    )
                    DropdownMenu(
                        expanded = expandedService,
                        onDismissRequest = { expandedService = !expandedService })
                    {
                        listService.forEach { service ->
                            DropdownMenuItem(
                                text = { Text(text = service) },
                                onClick = {
                                    servico = service
                                    expandedService = !expandedService
                                }
                            )
                        }
                    }
                }
            }
        }





    }
}