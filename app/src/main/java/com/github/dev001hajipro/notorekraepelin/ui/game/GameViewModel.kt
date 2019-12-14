package com.github.dev001hajipro.notorekraepelin.ui.game

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.github.dev001hajipro.notorekraepelin.Kraepelin

class GameViewModel(application: Application) : AndroidViewModel(application) {

    // UI側から変更なし
    var currentSecond = 0
    var cursorIndex = 0
    // UI側から変更なし
    var lines = MutableList(15) { Kraepelin.geneList115() }
    // UI側から変更なし
    var scores  = MutableList(15) {0}
    //
    var misses = MutableList(15) {0}
    // UI側から変更なし
    var lineCount = 0

    // UI側から変更なし
    var maxSecond = 0

    fun onClickCancel() {

    }
    fun onClickNumberPad(number : Int) {
        Log.d("DEBUG_X", "onClickNumberPad = $number")
    }
}