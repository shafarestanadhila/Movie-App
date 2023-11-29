package com.example.movieapp.ui.component.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.Poppins

@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .padding(horizontal = 45.dp, vertical = 5.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileImage()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Nadhila Shafaresta",
                modifier = Modifier
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.primary,
                fontFamily = Poppins,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "shafarestanadhila@apps.ipb.ac.id",
                modifier = Modifier . padding (
                    top = 7.dp),
                color = MaterialTheme.colorScheme.tertiary,
                fontFamily = Poppins,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = "A Junior Software Engineering Technology Student at IPB University " +
                        "who seeks challenges for self-improvement and self-development. " +
                        "Ability to organise and prioritise and effectively balance multiple priorities, " +
                        "also work under pressure and meet strategic deadlines. Passionate about technology, design, and business. " +
                        "Good analytical and coding skills, proficient in HTML, CSS, JavaScript, Java, Phyton, and Kotlin.",
                modifier = Modifier.padding(top = 22.dp),
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = Poppins,
                fontSize = 11.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfilePagePreview() {
    MovieAppTheme {
        ProfilePage(
            navController = rememberNavController()
        )
    }
}