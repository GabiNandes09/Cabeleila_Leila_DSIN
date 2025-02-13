package com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielfernandes.cabeleleilaleila.R

@Composable
fun MainBottonBar() {
    val items = listOf(
        BottomItem(label = "Resume", Icons.AutoMirrored.Filled.List),
        BottomItem(label = "Home", Icons.Default.Home),
        BottomItem(
            label = "Agendar",
            ImageVector.Companion.vectorResource(id = R.drawable.schedule_ico)
        ),
    )

    BottomAppBar(
        actions = {
            items.forEach { item ->
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = {
                        Icon(
                            item.icon,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    },
                    label = { Text(text = item.label, color = Color.Black) }
                )
            }
        },
        containerColor = Color.LightGray,
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
    )
}

@Preview
@Composable
private fun Preview() {
    MainBottonBar()
}

private data class BottomItem(
    val label: String,
    val icon: ImageVector
)