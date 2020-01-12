package com.github.dev001hajipro.notorekraepelin.ui.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GameResultViewModel(application: Application) : AndroidViewModel(application) {

    private val _numberOfCorrectAnswers = MutableLiveData(0)
    val numberOfCorrectAnswers: LiveData<Int> = _numberOfCorrectAnswers

    private val _numberOfIncorrectAnswers = MutableLiveData(0)
    val numberOfIncorrectAnswers: LiveData<Int> = _numberOfIncorrectAnswers

    private val _grade = MutableLiveData(0f)
    var grade: LiveData<Float> = _grade

    fun init(correctAnswers: Int, incorrectAnswers: Int, grade: Float) {
        _numberOfCorrectAnswers.value = correctAnswers
        _numberOfIncorrectAnswers.value = incorrectAnswers
        _grade.value = grade
    }
}