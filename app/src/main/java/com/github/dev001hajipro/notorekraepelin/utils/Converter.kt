package com.github.dev001hajipro.notorekraepelin.utils

import android.content.Context
import androidx.databinding.InverseMethod
import com.github.dev001hajipro.notorekraepelin.R

object Converter {
    fun nameToTime(context: Context, name: String) = when (name) {
        context.getString(R.string.minutes_1) -> 60
        context.getString(R.string.minutes_2) -> 120
        context.getString(R.string.minutes_3) -> 180
        context.getString(R.string.minutes_5) -> 300
        context.getString(R.string.minutes_10) -> 600
        context.getString(R.string.minutes_15) -> 900
        else -> 60
    }

    @InverseMethod(value = "nameToTime")
    fun timeToName(context: Context, seconds: Int): String = when (seconds) {
        60 -> context.getString(R.string.minutes_1)
        120 -> context.getString(R.string.minutes_2)
        180 -> context.getString(R.string.minutes_3)
        300 -> context.getString(R.string.minutes_5)
        600 -> context.getString(R.string.minutes_10)
        900 -> context.getString(R.string.minutes_15)
        else -> context.getString(R.string.minutes_1)
    }

    // XMLではJava形式で呼び出す必要がある。
    // JvmStaticを使うと、Converter.gradeAlphabetで呼び出せる。
    // 使わない場合は、Converter.INSTANCE.nameToTimeのように呼び出す。
    @JvmStatic
    fun gradeAlphabet(n: Float) = when {
        // 平均60秒で55問解いたらA+判定
        n > (55f / 60f) -> "A+"
        n > (40f / 60f) -> "A"
        n > (25f / 60f) -> "B"
        n > (10f / 60f) -> "C"
        n > (9f / 60f) -> "D"
        else -> "E"
    }
}