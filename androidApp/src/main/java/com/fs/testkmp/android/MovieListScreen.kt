package com.fs.testkmp.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.fs.testkmp.Main
import com.fs.testkmp.data.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

@Composable
fun MovieContent(
    main: Main,
    lifecycleScope: CoroutineScope
) {
    val state = main.sendRequest()
        .stateIn(lifecycleScope, SharingStarted.Eagerly, emptyList()).collectAsState()



    MaterialTheme {
        Surface() {
            Scaffold(topBar = {
                TopAppBar(title = { Text("Test KMP App") })
            }) {
                LazyColumn(contentPadding = it) {
                    items(items = state.value) {
                        Timber.tag("MovieContent").e(it.posterPath)
                        MovieHolder(
                            item = it,
                            action = {

                            },
                            painter = rememberImagePainter(
                                data = it.posterPath.toMovieThumbnailUrl(),
                                builder = {
                                    placeholder(R.drawable.ic_android_black_24dp)
                                    error(R.drawable.ic_android_black_24dp)
                                }
                            ),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieHolder(item: Movie, action: (Movie) -> Unit, painter: Painter) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)) {
            Image(
                painter = rememberImagePainter(
                    data = item.posterPath.toMovieThumbnailUrl(),
                    builder = {
                        placeholder(R.drawable.ic_android_black_24dp)
                        error(R.drawable.ic_android_black_24dp)
                    }
                ),
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                contentDescription = ""
            )
            Text(text = item.title, modifier = Modifier
                .clickable { action(item) }
                .fillMaxWidth()
                .padding(start = 16.dp, 0.dp, 0.dp, 0.dp)
            )
        }
    }
}

private fun String.toMovieThumbnailUrl() =
    "https://image.tmdb.org/t/p/w185$this"

@Preview
@Composable
fun MovieItemPreview() {
    val resourcePainter = painterResource(id = R.drawable.ic_android_black_24dp)
    MovieHolder(item = Movie(
        id = null,
        title = "Some movie",
        voteCount = null,
        voteAverage = null,
        posterPath = "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
        releaseDate = "",
        overview = "",
        genres = listOf()
    ), action = {}, painter = resourcePainter
    )
}
