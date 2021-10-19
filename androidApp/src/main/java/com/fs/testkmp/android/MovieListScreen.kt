package com.fs.testkmp.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.fs.testkmp.Main
import com.fs.testkmp.data.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@Composable
fun MovieContent(
    main: Main,
    lifecycleScope: CoroutineScope
) {
    val state = main.sendRequest()
        .stateIn(lifecycleScope, SharingStarted.Eagerly, emptyList()).collectAsState()

    MaterialTheme {
        Surface {
            Scaffold(topBar = {
                TopAppBar(title = { Text("Test KMP App") })
            }) {
                LazyColumn(contentPadding = it) {
                    items(items = state.value) {
                        MovieHolder(item = it, action = {

                        })
                    }
                }
            }
        }
    }
}

@Composable
fun MovieHolder(item: Movie, action: (Movie) -> Unit) {
    Text(text = item.title, Modifier.clickable {
        action(item)
    })
}
