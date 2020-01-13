package com.github.dev001hajipro.notorekraepelin.ui.game

import android.app.Application
import android.os.Handler
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.github.dev001hajipro.notorekraepelin.Event
import com.github.dev001hajipro.notorekraepelin.Kraepelin

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val tag: String = GameViewModel::class.java.simpleName

    private val countDownInterval = 1000L
    private val handler = Handler()

    // 制限時間
    private val _secondsUntilFinished = MutableLiveData(0)
    val secondsUntilFinished: LiveData<Int> = _secondsUntilFinished

    // 経過時間
    private val _elapsedSeconds = MutableLiveData(0)
    val elapsedSeconds: LiveData<Int> = _elapsedSeconds

    // 残り時間 = 制限時間 - 経過時間
    val remainingSeconds = Transformations.map(elapsedSeconds) { n ->
        _secondsUntilFinished.value?.minus(n)
    }

    var q1 = MutableLiveData(0)
    var q2 = MutableLiveData(0)
    var a1 = MutableLiveData(0)
    var a2 = MutableLiveData(0)
    var a3 = MutableLiveData(0)

    var cursorIndex = 0
    // UI側から変更なし
    private var lines = MutableList(15) { Kraepelin.generateTest() }
    // UI側から変更なし
    // 一行ごとの正解数
    private var scores = MutableList(15) { 0 }

    fun sumScore() = scores.sum()

    // 一行ごとの不正解数
    private var misses = MutableList(15) { 0 }

    fun sumMiss() = misses.sum()

    // 評価=正解数/秒
    fun grade(): Float {
        return (scores.sum().toFloat() / (secondsUntilFinished.value ?: 60).toFloat())
    }

    private var lineCount = 0

    private val _navigateToGameResultEvent = MutableLiveData<Event<String>>()
    val navigateToGameResultEvent: LiveData<Event<String>> = _navigateToGameResultEvent

    private val runnable = object : Runnable {
        override fun run() {
            _elapsedSeconds.value = (_elapsedSeconds.value ?: 0) + 1
            val v = _elapsedSeconds.value ?: 0

            if (v % 60 == 0) { // イベント
                cursorIndex = 0
                lineCount++
            }

            secondsUntilFinished.value?.let {
                if ((elapsedSeconds.value ?: 0) < it) {
                    Log.d(tag, "before postDelayed delayMillis=1000, ${_elapsedSeconds.value}")
                    handler.postDelayed(this, countDownInterval)
                } else {
                    _navigateToGameResultEvent.value = Event("")
                }
            }
        }
    }

    fun init(seconds: Int = 60) {
        Log.d(tag, "init")

        _secondsUntilFinished.value = seconds
        _elapsedSeconds.value = 0

        lineCount = 0
        cursorIndex = 0

        q1.value = lines[lineCount][cursorIndex + 0]
        q2.value = lines[lineCount][cursorIndex + 1]
    }

    fun onResume() {
        Log.d(tag, "before postDelayed delayMillis=1000, ${_elapsedSeconds.value}")
        handler.postDelayed(runnable, countDownInterval) // start timer.
    }

    fun onPause() {
        handler.removeCallbacks(runnable)
    }

    // TODO クリアボタンで、１つ前の問題に戻る対応。
    // 行の最初の場合は、戻れない仕様。
    fun onClickCancel() {

    }

    fun onClickNumberPad(number: Int) {
        Log.d("DEBUG_X", "onClickNumberPad = $number")
        // TODO とりあえず、一つだけ表示。最終的にはリストで表示して、アニメーションしたい
        a2.value = number
        val answer = (lines[lineCount][cursorIndex + 0] + lines[lineCount][cursorIndex + 1]) % 10
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
