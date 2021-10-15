package com.fs.testkmp.android

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import com.fs.testkmp.Main
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val main = Main()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        plantTimberTree()

        setContent {
            MovieContent()
        }
    }

    private fun plantTimberTree() {
        Timber.plant(Timber.DebugTree())
        StrictMode.enableDefaults()
    }

    @Composable
    fun MovieContent() {
        val state = main.sendRequest()
            .stateIn(lifecycleScope, SharingStarted.Eagerly, emptyList()).collectAsState()

        Surface {
            LazyColumn {
                items(items = state.value) {
                    Text(text = it.title)
                }
            }
        }
    }
}
