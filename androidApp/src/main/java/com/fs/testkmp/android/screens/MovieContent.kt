package com.fs.testkmp.android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.fs.testkmp.android.MainViewModel
import com.fs.testkmp.android.R
import com.fs.testkmp.data.Genre
import com.fs.testkmp.data.MovieDetails
import com.fs.testkmp.data.ProductionCountry
import com.fs.testkmp.data.toMovieThumbnailUrl
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieContent(
    id: Int,
    navController: NavHostController,
    viewModel: MainViewModel = getViewModel()
) {

    val movie = remember {
        viewModel.getMovieById(id)
    }

    movie.collectAsState().value?.also {
        MovieDetailsContent(
            details = it,
            painter = rememberImagePainter(
                data = it.toMovieThumbnailUrl(),
                builder = {
                    placeholder(R.drawable.ic_android_black_24dp)
                    error(R.drawable.ic_android_black_24dp)
                }
            ),
            action = {
                navController.navigate(Screen.Trailer.title + "/$it")
            })
    }
}

@Composable
private fun MovieDetailsContent(
    details: MovieDetails,
    painter: Painter,
    action: (String) -> Unit
) {
    Column(Modifier.background(Color.White)) {
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
                    data = details.toMovieThumbnailUrl(),
                    builder = {
                        placeholder(R.drawable.ic_android_black_24dp)
                        error(R.drawable.ic_android_black_24dp)
                    }
                ),
                contentDescription = ""
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = details.title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Text(
                    text = details.genres.joinToString(", ") { it.name },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color.LightGray
                )
                Text(
                    text = details.productionCountries.firstOrNull()?.name ?: "Just",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color.Black
                )
                Text(
                    text = details.releaseDate,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = Color.Black
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        action(details.id.toString())
                    }) {
                    Text(
                        text = "Trailer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = Color.Black
                    )
                }
            }
        }
        Text(
            text = details.overview,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun MoviePreview() {
    val resourcePainter = painterResource(id = R.drawable.ic_android_black_24dp)
    MovieDetailsContent(
        details = MovieDetails(
            id = 123,
            title = "Some movie",
            voteCount = 123,
            voteAverage = 8.8,
            posterPath = "/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
            releaseDate = "23.08.2011",
            overview = "Bla bla bla bla bla bla",
            productionCountries = listOf(ProductionCountry("", "USA")),
            genres = listOf(Genre(1, "tag"), Genre(2, "tag2")),
        ), painter = resourcePainter, {}
    )
}
