package com.thirdwinter.mobiletechtest.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.thirdwinter.mobiletechtest.data.model.Article
import com.thirdwinter.mobiletechtest.viewmodel.NewsArticleViewModel


@Composable
fun HomeScreen(viewModel: NewsArticleViewModel = hiltViewModel()) {

    val res = viewModel.articles.value

    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize())
        {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

    if (res.errorMessage.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.errorMessage, modifier = Modifier.align(Alignment.Center))
        }
    }

    res.data?.let {
        LazyColumn {
            items(it) {
                ArticleItem(it)
            }
        }
    }


}

@Composable
fun ArticleItem(it: Article) {

    Box(modifier = Modifier) {

        Image(
            painter = rememberImagePainter(data = it.urlToImage), contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(Color(red = 0, blue = 0, green = 0, alpha = 150))
        ) {
            Text(
                text = it.title,
                style = androidx.compose.ui.text.TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold, fontSize = 15.sp,

                    ),
                modifier = Modifier.padding(start = 12.dp, bottom = 12.dp)
            )

            if (it.author.isNotEmpty()) {
                Text(
                    text = it.author,
                    style = androidx.compose.ui.text.TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Normal, fontSize = 13.sp
                    ),
                    modifier = Modifier.padding(start = 12.dp, bottom = 12.dp)
                )
            }

        }
    }


}


