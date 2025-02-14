package com.gabrielfernandes.cabeleleilaleila.ui.screen.mainUI.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielfernandes.cabeleleilaleila.R
import com.gabrielfernandes.cabeleleilaleila.models.User

@Composable
fun HeadUI(user: User, onBackPress: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 20.dp, horizontal = 10.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {onBackPress()},
            modifier = Modifier.padding(end = 10.dp)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        ) {
            Text(
                text = "Olá,",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Text(
                text = user.username.uppercase(),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HeadUI(User(0, "", "", ""), {})
}