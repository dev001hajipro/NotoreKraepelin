package com.github.dev001hajipro.notorekraepelin.ui.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SelectGameViewModel(application: Application) : AndroidViewModel(application) {
    var seconds = MutableLiveData("10")
}