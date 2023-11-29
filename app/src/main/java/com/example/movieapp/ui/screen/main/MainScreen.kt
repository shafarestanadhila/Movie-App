package com.example.movieapp.ui.screen.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.data.MoviesRepository
import com.example.movieapp.model.MoviesData
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.Poppins
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen (
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: MainViewModel = viewModel(factory = ViewModelFactory(MoviesRepository()))
){
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Movies App",
                    color = (MaterialTheme.colorScheme.primary),
                    fontFamily = Poppins,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium

                )
            },
            actions = {
                AppBarProfileActions(navController = navController)
            }
        )

        val query by viewModel.query
        val searchResults by viewModel.searchResults

        SearchBar(
            query = query,
            onQueryChange = viewModel::search,
            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
        )

        Box(modifier = modifier) {
            val scope = rememberCoroutineScope()
            val listState = rememberLazyListState()
            val showButton: Boolean by remember {
                derivedStateOf { listState.firstVisibleItemIndex > 0 }
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(start = 12.dp, bottom = 80.dp, end = 12.dp)
            ) {
                val itemsToDisplay = if (query.isNotBlank() && searchResults.isNotEmpty()) {
                    searchResults
                } else {
                    MoviesData.movies
                }

                items(itemsToDisplay, key = { it.id }) { movies ->
                    MoviesListItem(
                        name = movies.name,
                        photoUrl = movies.photoUrl,
                        movieId = movies.id,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 100)),
                        navController = navController
                    )
                }
            }
            this@Column.AnimatedVisibility(
                visible = showButton,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically(),
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .align(Alignment.BottomCenter)
            ) {
                ScrollToTopButton(
                    onClick = {
                        scope.launch {
                            listState.scrollToItem(index = 0)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AppBarProfileActions(
    navController: NavController
){
    Box  {
        AboutAction(navController = navController)
    }
}

@Composable
fun AboutAction(
    navController: NavController
) {
    IconButton(onClick = {
        navController.navigate("AboutScreen")
    }) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_person_24),
            contentDescription = "about_page",
            tint = (MaterialTheme.colorScheme.secondary)
        )
    }
}

@Composable
fun MoviesListItem(
    name: String,
    photoUrl: String,
    movieId: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {
            navController.navigate("DetailScreen/$movieId")
        }
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(80.dp, 120.dp)
        )
        Text(
            text = name,
            fontFamily = Poppins,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 5.dp)
        )
    }
}

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(R.string.scroll_to_top),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        },
        placeholder = {
            Text(stringResource(R.string.search_movie))
        },
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {
    }
}

@Preview(showBackground = true)
@Composable
fun HeroListItemPreview() {
    MovieAppTheme {
        MoviesListItem(
            name = "Barbie",
            photoUrl = "",
            movieId = "1",
            navController = rememberNavController()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    MovieAppTheme {
        MainScreen( navController = rememberNavController())
    }
}