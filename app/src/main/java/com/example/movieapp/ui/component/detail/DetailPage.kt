package com.example.movieapp.ui.component.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.Poppins

@Composable
fun DetailPage(
    name: String,
    photoUrl: String,
    description: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 45.dp, vertical = 5.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp, 320.dp)
            )
            Text(
                text = name,
                modifier = Modifier
                    .padding(top = 10.dp),
                color = MaterialTheme.colorScheme.primary,
                fontFamily = Poppins,
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = description,
                modifier = Modifier.padding(top = 16.dp),
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = Poppins,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailPagePreview() {
    MovieAppTheme {
        DetailPage(
            name = "Barbie",
            photoUrl = "",
            description = "aaa",
            navController = rememberNavController()
        )
    }
}