package com.saad.tweetlify.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.saad.tweetlify.R
import com.saad.tweetlify.viewmodels.CategoryViewModel
import kotlinx.coroutines.delay

@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val categories = categoryViewModel.categories.collectAsState()
    val degree = produceState(initialValue = 0) {
        while (true) {
            delay(16)
            value = (value + 10) % 360
        }
    }
    if (categories.value.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                imageVector = Icons.Default.Refresh,
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .rotate(degree.value.toFloat())
            )
        }
    } else {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            items(categories.value.toList()) {
                CategoryItem(category = it, onClick)

            }
        }
    }
}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .clickable {
                onClick(category)
            }
            .padding(4.dp)
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .paint(
                painter = painterResource(id = R.drawable.bg),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, Color(0XFFEEEEEE)),
        contentAlignment = Alignment.BottomCenter,

        ) {
        Text(
            text = category,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.titleLarge
        )
    }
}