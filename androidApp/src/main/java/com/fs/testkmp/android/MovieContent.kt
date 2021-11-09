package com.fs.testkmp.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.fs.testkmp.Main
import com.fs.testkmp.data.MovieDetails
import com.fs.testkmp.data.toMovieThumbnailUrl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
fun MovieContent(
    id: Int,
    viewModel: MainViewModel = getViewModel()
) {

    val movie = remember {
        viewModel.getMovieById(id)
    }

    Timber.e("Movie: $id")

    movie.collectAsState().value?.also {
        MovieDetailsContent(
            movieDetails = it,
            painter = rememberImagePainter(
                data = it.toMovieThumbnailUrl(),
                builder = {
                    placeholder(R.drawable.ic_android_black_24dp)
                    error(R.drawable.ic_android_black_24dp)
                }
            ))
    }
}

@Composable
private fun MovieDetailsContent(
    movieDetails: MovieDetails,
    painter: Painter
) {

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)) {
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
                    data = "https://image.tmdb.org/t/p/w185/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                    builder = {
                        placeholder(R.drawable.ic_android_black_24dp)
                        error(R.drawable.ic_android_black_24dp)
                    }
                ),
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
private fun MoviePreview() {
    val resourcePainter = painterResource(id = R.drawable.ic_android_black_24dp)
    MovieDetailsContent(
        movieDetails = MovieDetails(
            id = 123,
            title = "Some movie",
            voteCount = 123,
            voteAverage = 8.8,
            posterPath = "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
            releaseDate = "23.08.2011",
            overview = "Bla bla bla bla bla bla",
            genres = listOf(),

        ), painter = resourcePainter
    )
}
