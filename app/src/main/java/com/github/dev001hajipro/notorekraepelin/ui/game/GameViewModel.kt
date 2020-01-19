package com.github.dev001hajipro.notorekraepelin.ui.game

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.dev001hajipro.notorekraepelin.Event
import com.github.dev001hajipro.notorekraepelin.Kraepelin

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val tag: String = GameViewModel::class.java.simpleName

    // タイマーℤ
    private lateinit var timer: CountDownTimer
    private val _millisUntilFinishedForResume = MutableLiveData<Long>(0L)
    var millisUntilFinishedForResume = _millisUntilFinishedForResume

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
        return (scores.sum().toFloat() / ((millisUntilFinishedForResume.value
            ?: 60000) / 1000L).toFloat())
    }

    private var lineCount = 0

    private val _navigateToGameResultEvent = MutableLiveData<Event<String>>()
    val navigateToGameResultEvent: LiveData<Event<String>> = _navigateToGameResultEvent

    private fun timer(millisInFuture: Long, countDownInterval: Long = 100L): CountDownTimer {
        return object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onFinish() {
                _navigateToGameResultEvent.value = Event("")
            }

            override fun onTick(millisUntilFinished: Long) {
                millisUntilFinishedForResume.value = millisUntilFinished
                if ((((millisInFuture - millisUntilFinished) / 1000L) % 60) == 0L) { // イベント
                    cursorIndex = 0
                    lineCount++
                }
            }
        }
    }

    fun init(milliseconds: Long = 60000) {
        Log.d(tag, "init")
        millisUntilFinishedForResume.value = milliseconds

        lineCount = 0
        cursorIndex = 0

        q1.value = lines[lineCount][cursorIndex + 0]
        q2.value = lines[lineCount][cursorIndex + 1]
    }

    fun onResume() {
        timer = timer(millisUntilFinishedForResume.value ?: 0)
        timer.start()
    }

    fun onPause() {
        timer.cancel()
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
