package com.github.dev001hajipro.notorekraepelin.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.dev001hajipro.notorekraepelin.R
import com.github.dev001hajipro.notorekraepelin.databinding.FragmentGameResultBinding
import kotlinx.android.synthetic.main.fragment_game_result.*


/**
 * １ゲーム終わったときの結果画面用フラグメント.
 */
class GameResultFragment : Fragment() {
    private val args: GameResultFragmentArgs by navArgs()
    private val viewModel: GameResultViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGameResultBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = this.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonGotoTitle.setOnClickListener {
            findNavController().navigate(GameResultFragmentDirections.actionNavGameResultToNavSelectGame())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.numberOfCorrectAnswers.value = args.correctAnswers
        viewModel.numberOfIncorrectAnswers.value = args.incorrectAnswers
    }


}
