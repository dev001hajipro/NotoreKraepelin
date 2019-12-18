package com.github.dev001hajipro.notorekraepelin.ui.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.dev001hajipro.notorekraepelin.R
import com.github.dev001hajipro.notorekraepelin.databinding.FragmentSelectGameBinding
import kotlinx.android.synthetic.main.fragment_select_game.*

/**
 * ゲームタイトル（ゲームモード選択）画面用フラグメント.
 */
class SelectGameFragment : Fragment() {
    private val viewModel: SelectGameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectGameBinding.inflate(
            inflater,
            container,
            false
        ).also {
            it.lifecycleOwner = this
            it.viewModel = this.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filled_exposed_dropdown.setAdapter(
            ArrayAdapter<String>(
                requireContext(),
                R.layout.dropdown_menu_popup_item,
                arrayOf("10", "60", "300", "600", "900", "1800")
            )
        )
        // TODO("secondsの値をリセットすれば、AutoCompleteTextViewの候補リストが表示されるが、viewModel.resetやviewModel.initにしたほうが良い。")
        viewModel.seconds.value = ""

        start_game.setOnClickListener {
            Log.d("DEBUG_B", "viewModel.seconds = ${viewModel.seconds}")
            // TODO("i want auto cast from String to Int. adapter, converter?")
            val s = viewModel.seconds.value?.toInt() ?: 444
            findNavController().navigate(SelectGameFragmentDirections.actionNavSelectGameToNavGame(s))
        }
    }
}
