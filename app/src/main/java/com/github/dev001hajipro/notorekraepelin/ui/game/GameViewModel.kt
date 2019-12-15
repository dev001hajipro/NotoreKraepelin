package com.github.dev001hajipro.notorekraepelin.ui.game

import android.app.Application
import android.content.Intent
import android.graphics.Color // TODO("delete ui code?")
import android.os.Handler
import android.util.Log
import android.widget.Button // TODO("delete ui code")
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.dev001hajipro.notorekraepelin.Kraepelin

class GameViewModel(application: Application) : AndroidViewModel(application) {

    val handler = Handler()
    var timerText = MutableLiveData<String>("0")

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
    var maxSecond = 60

    private val runnable = object : Runnable {
        override fun run() {
            currentSecond++
            // TODO("need observe")
            //textViewTimer.text = "残り${maxSecond - currentSecond}秒"

            if (currentSecond % 60 == 0) {
                cursorIndex = 0
                lineCount++
            }

            if (currentSecond < maxSecond) {
                handler.postDelayed(this, 1000)
            } else { // time over
                handler.removeCallbacks(this)
                //TODO("goto the GameResultFragment."
                //startActivity(Intent(application, ResultActivity::class.java))
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

        // TODO("fragment argument")
        //maxSecond = intent.getIntExtra("KEY_TIMER_SECONDS", 1)

        // TODO("need binding")
        timerText.value = "残り${maxSecond - currentSecond}秒"
// TODO("need binding")
        //textQ1.text = "${lines[lineCount][cursorIndex + 0]}"
        //textQ2.text = "${lines[lineCount][cursorIndex + 1]}"
// TODO("need binding")
        //textA1.text = " "
        //textA2.text = "_"
        //textA3.text = " "

        // start timer.
        handler.postDelayed(runnable, 1000)
    }
    fun onClickNumberPad(number : Int) {
        Log.d("DEBUG_X", "onClickNumberPad = $number")
        // show input number
        // TODO("delete ui code")
        /*
        val b = v as Button
        var inputNumber = Integer.parseInt(b.tag.toString())
        textA1.text = b.tag.toString()

        val answer = (lines[lineCount][cursorIndex + 0] + lines[lineCount][cursorIndex + 1]) % 10

        if (answer == inputNumber) {
            textA1.setTextColor(Color.GREEN)
            scores[lineCount]++
        } else {
            textA1.setTextColor(Color.RED)
            misses[lineCount]++
        }
         */

        // show next question.
        cursorIndex++
        // TODO("update ui")
        //textQ1.text = "${lines[lineCount][cursorIndex + 0]}"
        //textQ2.text = "${lines[lineCount][cursorIndex + 1]}"
    }
}