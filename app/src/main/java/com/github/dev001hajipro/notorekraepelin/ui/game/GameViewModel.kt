package com.github.dev001hajipro.notorekraepelin.ui.game

import android.app.Application
import android.os.Handler
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.dev001hajipro.notorekraepelin.Kraepelin
import com.github.dev001hajipro.notorekraepelin.SingleLiveEvent

class GameViewModel(application: Application) : AndroidViewModel(application) {

    val handler = Handler()
    var remainingSeconds = MutableLiveData(0)

    // UI側から変更なし
    var currentSecond = 0
    var cursorIndex = 0

    var q1 = MutableLiveData(0)
    var q2 = MutableLiveData(0)
    var a1 = MutableLiveData(0)
    var a2 = MutableLiveData(0)
    var a3 = MutableLiveData(0)

    // UI側から変更なし
    var lines = MutableList(15) { Kraepelin.geneList115() }
    // UI側から変更なし
    var scores = MutableList(15) { 0 }
    //
    var misses = MutableList(15) { 0 }
    // UI側から変更なし
    var lineCount = 0

    // UI側から変更なし
    var maxSecond = MutableLiveData(0)

    var navigateToGameResultEvent = SingleLiveEvent<Any>()

    private val runnable = object : Runnable {
        override fun run() {
            currentSecond++
            // TODO("need observe")
            remainingSeconds.value = maxSecond.value?.minus(currentSecond) ?: 0

            if (currentSecond % 60 == 0) {
                cursorIndex = 0
                lineCount++
            }

            maxSecond.value?.let {
                if (currentSecond < it) {
                    handler.postDelayed(this, 1000)
                } else {
                    navigateToGameResultEvent.setValue(Any())
                }
            }
        }
    }

    fun startTimer() {
        // start timer.
        handler.postDelayed(runnable, 1000)
    }

    fun stopTimer() {
        handler.removeCallbacks(runnable)
    }

    fun onClickCancel() {

    }

    fun start() {
        Log.d("onStart", "onStart")

        currentSecond = 0
        cursorIndex = 0
        lineCount = 0

        remainingSeconds.value = maxSecond.value?.minus(currentSecond) ?: 0

        q1.value = lines[lineCount][cursorIndex + 0]
        q2.value = lines[lineCount][cursorIndex + 1]

        // start timer.
        handler.postDelayed(runnable, 1000)
    }

    fun onClickNumberPad(number: Int) {
        Log.d("DEBUG_X", "onClickNumberPad = $number")

        a1.value = number
        val answer = lines[lineCount][cursorIndex + 0] + lines[lineCount][cursorIndex + 1] % 10
        // todo("setTextColor")
        if (answer == number) {
            scores[lineCount]++
        } else {
            misses[lineCount]++
        }

        // show data of the next question.
        cursorIndex++
        q1.value = lines[lineCount][cursorIndex + 0]
        q2.value = lines[lineCount][cursorIndex + 1]
    }
}