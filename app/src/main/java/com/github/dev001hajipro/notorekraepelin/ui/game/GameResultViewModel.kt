package com.github.dev001hajipro.notorekraepelin.ui.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class GameResultViewModel(application: Application) : AndroidViewModel(application) {
    var numberOfCorrectAnswers = MutableLiveData(0)
    var numberOfIncorrectAnswers = MutableLiveData(0)
    var grade = MutableLiveData("A")
}