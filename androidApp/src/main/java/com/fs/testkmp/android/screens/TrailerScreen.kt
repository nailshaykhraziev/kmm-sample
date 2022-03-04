package com.fs.testkmp.android.screens

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun TrailerScreen(url: String) {

    val context = LocalContext.current

    val player = remember {
        SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(
                MediaItem.fromUri("https://www.youtube.com/watch?v=V0hagz_8L3M")
            )
            prepare()
            playWhenReady = true
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        DisposableEffect(
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = {
                    PlayerView(it).apply {
                        this.player = player
                        layoutParams =
                            FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                    }
                }), effect = {
                onDispose {
                    player.release()
                }
            })

    }
}
