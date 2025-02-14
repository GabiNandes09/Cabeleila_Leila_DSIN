package com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DefaultComboBox(
    list: List<Any>
) {
    var servico by remember {
        mutableStateOf("")
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    Column {
        TextField(
            value = servico,
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
                .padding(horizontal = 10.dp)
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
                                expanded = !expanded
                            }
                        }
                    }
                },
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.LightGray,
                focusedContainerColor = Color.White,
                errorContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = !expanded },
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            containerColor = Color.Transparent,
            tonalElevation = 0.dp,
            shadowElevation = 0.dp
        )
        {
            list.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.toString()) },
                    onClick = {
                        servico = item.toString()
                        expanded = !expanded
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(2.5.dp)
                        .background(Color.White)
                        .fillMaxWidth()
                        .heightIn(max = 200.dp)
                )
            }
        }
    }
}