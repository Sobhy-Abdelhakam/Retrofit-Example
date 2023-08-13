package dev.sobhy.retrofitexample.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import dev.sobhy.retrofitexample.data.model.Article
import dev.sobhy.retrofitexample.ui.screens.composable.ErrorDialog
import dev.sobhy.retrofitexample.ui.screens.composable.Loading

@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavHostController) {
    val state by viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        ErrorDialog(
            show = state.dialogModel?.show ?: false,
            message = state.dialogModel?.message ?: "",
            onDismiss = {
                viewModel.dismissDialog()
            }) {
            viewModel.dismissDialog()
            viewModel.getArticles()
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Loading(isLoading = state.isLoading, modifier = Modifier.align(Alignment.Center))
            LazyColumn() {
                items(state.list) {
                    HomeListItem(it) {article ->
                        viewModel.saveArticle(article)
                    }
                }
            }

            Button(
                onClick = {
                          navController.navigate("saved")
                          }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text(text = "Go to save screen")
            }
        }


    }
}

@Composable
fun HomeListItem(article: Article, onClick: (Article) -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(10.dp),

        ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {


            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "title : ", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                Text(text = article.title ?: "", fontSize = 17.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Description : ",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                text = article.description ?: "",
                fontSize = 17.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,

                )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { onClick(article) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(text = "Insert")
            }

        }


    }
}