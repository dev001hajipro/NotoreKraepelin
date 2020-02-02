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
    private val _millisecondsUntilFinished = MutableLiveData(0L)
    val millisecondsUntilFinished: LiveData<Long> = _millisecondsUntilFinished

    // 経過時間
    private val _elapsedMilliSeconds = MutableLiveData(0L)
    val elapsedMilliSeconds: LiveData<Long> = _elapsedMilliSeconds

    // 残り時間 = 制限時間 - 経過時間
    val remainingMilliseconds = Transformations.map(elapsedMilliSeconds) { n ->
        millisecondsUntilFinished.value?.minus(n)
    }

    var q1 = MutableLiveData(0)
    var q2 = MutableLiveData(0)
    var a1 = MutableLiveData(0)
    var a2 = MutableLiveData(0)
    var a3 = MutableLiveData(0)

    var cursorIndex = 0
    // UI側から変更なし
    private var lines = MutableList(15) { Kraepelin.generateTest() }

    private var lineCount = 0

    // UI側から変更なし
    // 一行ごとの正解数
    private var scores = MutableList(15) { 0 }

    fun sumScore() = scores.sum()

    // 一行ごとの不正解数
    private var misses = MutableList(15) { 0 }

    fun sumMiss() = misses.sum()

    // 評価=正解数/秒
    fun grade(): Float {
        return (scores.sum().toFloat() / ((_millisecondsUntilFinished.value
            ?: 60000) / 1000L).toFloat())
    }

    private val _navigateToGameResultEvent = MutableLiveData<Event<String>>()
    val navigateToGameResultEvent: LiveData<Event<String>> = _navigateToGameResultEvent

    private val runnable = object : Runnable {
        override fun run() {
            _elapsedMilliSeconds.value = (_elapsedMilliSeconds.value ?: 0) + 1000L
            val v = _elapsedMilliSeconds.value ?: 0

            if (v % 60000L == 0L) { // イベント
                Log.d(tag, "cursorIndex=$cursorIndex, lineCount=$lineCount")
                cursorIndex = 0
                lineCount++
            }

            _millisecondsUntilFinished.value?.let {
                if ((_elapsedMilliSeconds.value ?: 0) < it) {
                    Log.d(tag, "before postDelayed delayMillis=1000, ${_elapsedMilliSeconds.value}")
                    handler.postDelayed(this, countDownInterval)
                } else {
                    _navigateToGameResultEvent.value = Event("")
                }
            }
        }
    }

    fun init(milliseconds: Long = 60000L) {
        Log.d(tag, "init")
        _elapsedMilliSeconds.value = 0
        _millisecondsUntilFinished.value = milliseconds

        lineCount = 0
        cursorIndex = 0

        q1.value = lines[lineCount][cursorIndex + 0]
        q2.value = lines[lineCount][cursorIndex + 1]
    }

    fun onResume() {
        handler.postDelayed(runnable, countDownInterval)
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
