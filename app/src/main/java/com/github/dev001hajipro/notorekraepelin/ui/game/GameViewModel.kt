package com.github.dev001hajipro.notorekraepelin.ui.game

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class GameViewModel(application: Application) : AndroidViewModel(application) {

    fun onClickCancel() {

    }
    fun onClickNumberPad(number : Int) {
        Log.d("DEBUG_X", "onClickNumberPad = $number")

    }
}