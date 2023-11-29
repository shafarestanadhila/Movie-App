package com.example.movieapp.ui.screen.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.R
import com.example.movieapp.model.MoviesData
import com.example.movieapp.ui.component.detail.DetailPage
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.Poppins

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    movieId: String,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column{
        TopAppBar(
            title = {
                Text(
                    text = "Detail",
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    fontFamily = Poppins,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate("MainScreen")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.secondary
                    )

                }
            }
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ){
            Spacer(modifier = Modifier.height(16.dp))

            val movie = MoviesData.movies.find { it.id == movieId }

            if (movie != null) {
                DetailPage(
                    name = movie.name,
                    photoUrl = movie.photoUrl,
                    description = movie.description,
                    navController = navController
                )
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
    MovieAppTheme {
        DetailScreen(
            movieId = "1",
            navController = rememberNavController()
        )
    }
}