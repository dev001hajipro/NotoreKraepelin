package com.github.dev001hajipro.notorekraepelin.ui.anime

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class AnimeViewModel(application: Application) : AndroidViewModel(application) {
    var text1 = MutableLiveData("hello")
}