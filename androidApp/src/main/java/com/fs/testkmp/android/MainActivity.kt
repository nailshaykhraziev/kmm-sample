package com.fs.testkmp.android

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fs.testkmp.Main
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val main = Main()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        plantTimberTree()

        setContent {
            MovieContent(main, lifecycleScope)
        }
    }

    private fun plantTimberTree() {
        Timber.plant(Timber.DebugTree())
        StrictMode.enableDefaults()
    }
}
