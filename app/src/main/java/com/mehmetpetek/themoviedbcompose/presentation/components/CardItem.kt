package com.mehmetpetek.themoviedbcompose.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mehmetpetek.themoviedbcompose.BuildConfig
import com.mehmetpetek.themoviedbcompose.data.remote.model.Result
import kotlinx.collections.immutable.ImmutableList

@Composable
fun CardItem(
    title: String,
    items: ImmutableList<Result>?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(start = 10.dp), text = title,
            style = TextStyle(
                color = Color.Black,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )
        )
        items?.let {
            LazyRow(
                contentPadding = PaddingValues(10.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(it) {
                    AsyncImage(
                        model = "${BuildConfig.IMAGE_BASE_URL}/${it.poster_path}",
                        contentDescription = "",
                        contentScale = ContentScale.Inside,
                        onSuccess = { },
                        modifier = Modifier.size(width = 150.dp, height = 225.dp)
                    )
                }
            }
        }
    }
}