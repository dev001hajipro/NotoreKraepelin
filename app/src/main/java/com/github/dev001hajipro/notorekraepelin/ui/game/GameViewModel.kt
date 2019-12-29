package com.github.dev001hajipro.notorekraepelin.ui.game

import android.app.Application
import android.os.Handler
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.dev001hajipro.notorekraepelin.Kraepelin
import com.github.dev001hajipro.notorekraepelin.SingleLiveEvent

class GameViewModel(application: Application) : AndroidViewModel(application) {

    val tag = GameViewModel::class.java.simpleName

    private val handler = Handler()

    // 残り時間 = 制限時間 - 経過時間
    val remainingSeconds = MutableLiveData(0)
    fun calcRemainingSeconds() {
        remainingSeconds.value = maxSecond.value?.minus(elapsedSeconds.value ?: 0) ?: 0
    }
    // UI側から変更なし
    var maxSecond = MutableLiveData(0)
    // UI側から変更なし
    // 経過時間
    var elapsedSeconds = MutableLiveData(0)


    var q1 = MutableLiveData(0)
    var q2 = MutableLiveData(0)
    var a1 = MutableLiveData(0)
    var a2 = MutableLiveData(0)
    var a3 = MutableLiveData(0)

    var cursorIndex = 0
    // UI側から変更なし
    var lines = MutableList(15) { Kraepelin.geneList115() }
    // UI側から変更なし
    var scores = MutableList(15) { 0 }
    //
    var misses = MutableList(15) { 0 }
    // UI側から変更なし
    var lineCount = 0

    var navigateToGameResultEvent = SingleLiveEvent<Any>()

    private val runnable = object : Runnable {
        override fun run() {
            elapsedSeconds.value = (elapsedSeconds.value ?: 0) + 1
            val v = elapsedSeconds.value ?: 0
            calcRemainingSeconds()

            if (v % 60 == 0) { // イベント
                cursorIndex = 0
                lineCount++
            }

            maxSecond.value?.let {
                if ((elapsedSeconds.value ?: 0) < it) {
                    Log.d(tag, "before postDelayed delayMillis=1000, ${elapsedSeconds.value}")
                    handler.postDelayed(this, 1000)
                } else {
                    navigateToGameResultEvent.setValue(Any())
                }
            }
        }
    }

    fun onResume() {
        // start timer.
        Log.d(tag, "before postDelayed delayMillis=1000, ${elapsedSeconds.value}")
        handler.postDelayed(runnable, 1000)
    }

    fun onPause() {
        handler.removeCallbacks(runnable)
    }

    fun onClickCancel() {

    }

    fun start() {
        Log.d("onStart", "onStart")

        elapsedSeconds.value = 0
        calcRemainingSeconds()
        cursorIndex = 0
        lineCount = 0

        q1.value = lines[lineCount][cursorIndex + 0]
        q2.value = lines[lineCount][cursorIndex + 1]

        // start timer.
        handler.postDelayed(runnable, 1000)
    }

    fun onClickNumberPad(number: Int) {
        Log.d("DEBUG_X", "onClickNumberPad = $number")
        // TODO とりあえず、一つだけ表示。最終的にはリストで表示して、アニメーションしたい
        a2.value = number
        val answer = lines[lineCount][cursorIndex + 0] + lines[lineCount][cursorIndex + 1] % 10
        // TODO setTextColor
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