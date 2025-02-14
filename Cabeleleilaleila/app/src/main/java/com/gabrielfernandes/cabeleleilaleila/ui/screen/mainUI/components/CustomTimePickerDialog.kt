package com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI.components

import android.widget.NumberPicker
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collectLatest
import kotlin.math.exp

@Composable
fun CustomTimePickerDialog(
    initialHour: Int = 12,
    initialMinute: Int = 0,
    onTimeSelected: (Int, Int) -> Unit
) {
    var selectedHour by remember { mutableIntStateOf(initialHour) }
    var selectedMinute by remember { mutableIntStateOf(initialMinute) }
    var expanded by remember { mutableStateOf(false) }

    TextField(
        label = { Text(text = "Selecione o horário: ")},
        value = "${selectedHour}:${selectedMinute}",
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
    if (expanded){
        AlertDialog(
            onDismissRequest = { expanded = !expanded },
            title = { Text("Selecione um horário") },
            text = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    NumberPicker(
                        range = 8..16,
                        selectedValue = selectedHour,
                        onValueChange = { selectedHour = it }
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    NumberPicker(
                        range = 0..59,
                        selectedValue = selectedMinute,
                        onValueChange = { selectedMinute = it }
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onTimeSelected(selectedHour, selectedMinute)
                    expanded = !expanded
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { expanded = !expanded }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun NumberPicker(
    range: IntRange,
    selectedValue: Int,
    onValueChange: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(range.toList()) { value ->
            Text(
                text = value.toString(),
                fontSize = if (value == selectedValue) 24.sp else 16.sp,
                fontWeight = if (value == selectedValue) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier
                    .padding(4.dp)
                    .clickable { onValueChange(value) }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CustomTimePickerDialog(
        onTimeSelected = { hour, minute -> })
}

