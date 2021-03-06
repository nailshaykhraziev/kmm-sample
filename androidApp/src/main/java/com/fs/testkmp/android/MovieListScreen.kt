package com.fs.testkmp.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.fs.testkmp.data.Movie
import com.fs.testkmp.data.MovieEntity
import com.fs.testkmp.data.toMovieThumbnailUrl
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
fun MovieListContent(
    viewModel: MainViewModel = getViewModel(),
    onMovieClick: (id: Int) -> Unit = {}
) {

    val state = viewModel.state.collectAsState()

    LazyColumn {
        items(items = state.value) {
            Timber.tag("MovieContent").e(it.posterPath)
            MovieHolder(
                item = it,
                action = {
                    it.id?.let(onMovieClick)
                },
                painter = rememberImagePainter(
                    data = it.toMovieThumbnailUrl(),
                    builder = {
                        placeholder(R.drawable.ic_android_black_24dp)
                        error(R.drawable.ic_android_black_24dp)
                    }
                ),
            )
        }
    }
}

@Composable
fun MovieHolder(item: Movie, action: (Movie) -> Unit, painter: Painter) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
            .clickable {
                Timber
                    .tag("MovieHolder")
                    .e("clickable")
                action(item)
            }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, 8.dp)
        ) {
            Image(
                modifier = Modifier
                    .height(200.dp)
                    .aspectRatio(0.75f)
                    .align(Alignment.CenterVertically),
                painter = rememberImagePainter(
                    data = item.toMovieThumbnailUrl(),
                    builder = {
                        placeholder(R.drawable.ic_android_black_24dp)
                        error(R.drawable.ic_android_black_24dp)
                    }
                ),
                contentDescription = ""
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, 0.dp, 0.dp, 0.dp)
                )
                Text(
                    text = item.overview, color = Color.Gray, maxLines = 8,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, 0.dp, 0.dp, 0.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    val resourcePainter = painterResource(id = R.drawable.ic_android_black_24dp)
    MovieHolder(
        item = Movie(
            id = 1,
            title = "Some movie",
            voteCount = null,
            voteAverage = null,
            posterPath = "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
            releaseDate = "",
            overview = "Bla bla bla bla bla bla",
            genres = listOf()
        ), action = {}, painter = resourcePainter
    )
}
