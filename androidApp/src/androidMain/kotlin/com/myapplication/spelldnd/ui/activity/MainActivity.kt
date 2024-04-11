package com.myapplication.spelldnd.ui.activity

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import main.MainScreen
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

@ExperimentalAnimationApi
class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}