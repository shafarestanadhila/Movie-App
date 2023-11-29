package com.example.movieapp.ui.component.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.MovieAppTheme

@Composable
fun ProfileImage () {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box{
            Image(
                painterResource(id = R.drawable.background_profileimage),
                modifier = Modifier
                    .padding(8.dp)
                    .size(150.dp),
                contentDescription = "Background Profile Image",
            )
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .padding(18.dp)
                    .size(130.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.photo_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .size(1000.dp, 1000.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileImagePreview() {
    MovieAppTheme {
        ProfileImage()
    }
}