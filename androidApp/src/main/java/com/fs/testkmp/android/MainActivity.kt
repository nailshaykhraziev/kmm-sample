package com.fs.testkmp.android

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fs.testkmp.android.screens.MovieContent
import com.fs.testkmp.android.screens.MovieListContent
import com.fs.testkmp.android.screens.Screen
import com.fs.testkmp.android.screens.TrailerScreen
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        plantTimberTree()

        setContent {
            MainLayout()
        }
    }

    private fun plantTimberTree() {
        Timber.plant(Timber.DebugTree())
        StrictMode.enableDefaults()
    }

    @Composable
    fun MainLayout() {
        val navController = rememberNavController()
        MaterialTheme {
            Surface() {
                Scaffold(topBar = {
                    TopAppBar(title = { Text("Test KMP App") })
                }) {
                    MovieNavHost(
                        navController = navController
                    )
                }

            }
        }
    }

    @Composable
    fun MovieNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
        NavHost(
            navController = navController,
            startDestination = Screen.MovieList.title,
            modifier = modifier
        ) {
            composable(Screen.MovieList.title) {
                MovieListContent {
                    navController.navigate(Screen.MovieContent.title + "/$it")
                }
            }
            composable(Screen.MovieContent.title + "/{id}") {
                MovieContent(
                    id = it.arguments?.get("id").toString().toInt(),
                    navController = navController
                )
            }
            composable(Screen.Trailer.title + "/{url}") {
                TrailerScreen(
                    url = it.arguments?.get("url").toString(),
                )
            }
        }
    }
}
