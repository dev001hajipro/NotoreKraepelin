package com.github.dev001hajipro.notorekraepelin.ui.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SelectGameViewModel(application: Application) : AndroidViewModel(application) {

    // Two-way binding
    val seconds = MutableLiveData(0)

    // TODO デフォルトを60秒にしているが以下の方法で実装する。
    // 1.ユーザーの設定(preference)画面を用意
    // 2.そこでデフォルトの秒数を設定できるようにする。
    // 3.アプリ起動時には、ユーザー設定データを読み込むようにする。
    // これで、hardcodingがなくなる。
    fun init(seconds: Int = 60) {
        this.seconds.value = seconds
    }
}