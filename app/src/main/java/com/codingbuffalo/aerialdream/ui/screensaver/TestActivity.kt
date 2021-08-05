package com.codingbuffalo.aerialdream.ui.screensaver

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.codingbuffalo.aerialdream.R
import com.codingbuffalo.aerialdream.utils.WindowHelper

class TestActivity : Activity() {
    private var videoController: VideoController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.daydream_name)
        videoController = VideoController(this)
        val view = videoController!!.view
        setContentView(view)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_UP &&
                event.keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            videoController!!.skipVideo()
            return true
        }
        return super.dispatchKeyEvent(event)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            WindowHelper.hideSystemUI(window, videoController!!.view)
        }
    }

    override fun onStop() {
        videoController!!.stop()
        super.onStop()
    }
}