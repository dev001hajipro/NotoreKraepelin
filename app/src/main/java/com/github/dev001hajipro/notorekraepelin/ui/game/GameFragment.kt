package com.github.dev001hajipro.notorekraepelin.ui.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.vectordrawable.graphics.drawable.AnimatorInflaterCompat
import com.github.dev001hajipro.notorekraepelin.R
import com.github.dev001hajipro.notorekraepelin.databinding.FragmentGameBinding
import kotlinx.android.synthetic.main.fragment_game.*

/**
 * ゲーム画面用フラグメント.
 */
class GameFragment : Fragment() {

    private val args: GameFragmentArgs by navArgs()

    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGameBinding.inflate(
            inflater, container, false
        ).also {
            it.lifecycleOwner = this
            it.viewModel = this.viewModel
        }

        viewModel.navigateToGameResultEvent.observe(this.viewLifecycleOwner, Observer {
            findNavController().navigate(
                GameFragmentDirections.actionNavGameToNavGameResult(
                    viewModel.sumScore(),
                    viewModel.sumMiss(),
                    viewModel.grade()
                )
            )
        })

        viewModel.a2.observe(this.viewLifecycleOwner, Observer {
            arrayOf(textA2).forEach {
                AnimatorInflaterCompat.loadAnimator(
                    this@GameFragment.context,
                    R.animator.answer_number
                ).apply {
                    setTarget(it)
                }.start()
            }
        })

        viewModel.q1.observe(this.viewLifecycleOwner, Observer {
            arrayOf(textQ1, textQ2).forEach {
                AnimatorInflaterCompat.loadAnimator(
                    this@GameFragment.context,
                    R.animator.hide_numbers
                ).apply {
                    setTarget(it)
                }.start()
                AnimatorInflaterCompat.loadAnimator(
                    this@GameFragment.context,
                    R.animator.show_numbers
                ).apply {
                    setTarget(it)
                }.start()
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.secondsUntilFinished.value = args.seconds
        Log.d(this::class.java.simpleName, "args.seconds=${args.seconds}")
        viewModel.init()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }
}
