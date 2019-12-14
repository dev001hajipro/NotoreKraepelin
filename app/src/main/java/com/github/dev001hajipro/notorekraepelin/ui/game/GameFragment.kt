package com.github.dev001hajipro.notorekraepelin.ui.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.dev001hajipro.notorekraepelin.databinding.FragmentGameBinding

/**
 * ゲーム画面用フラグメント.
 */
class GameFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGameBinding.inflate(
            inflater, container, false
        ).also {
            it.lifecycleOwner = this
            it.viewModel = this.viewModel
        }
        //binding.lifecycleOwner = this
        //binding.viewModel = viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.d("DEBUG_X", "onStart")
    }
}
