package com.fs.testkmp.android

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.fs.testkmp.Main
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val main = Main()

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
                MovieListContent(main = main, lifecycleScope = lifecycleScope) {
                    navController.navigate(Screen.MovieContent.title + "/$it")
                }
            }
            composable(Screen.MovieContent.title + "/{id}") {
                MovieContent(id = it.arguments?.get("id").toString().toInt())
            }
        }
    }
}
