package com.github.dev001hajipro.notorekraepelin.ui.game

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
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
    private val _tag = SelectGameFragment::class.java.simpleName
    private val viewModel: SelectGameViewModel by viewModels()

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
            NoFilterArrayAdapter(
                requireContext(),
                R.layout.dropdown_menu_popup_item,
                arrayOf(
                    "10",
                    "60",
                    "300",
                    "600",
                    "900",
                    "1800"
                ) // TODO key-value方式にして、表示は、10秒、1分、3分...のようにする。
            )
        )

        start_game.setOnClickListener {
            Log.d(_tag, "viewModel.seconds = ${viewModel.seconds}")
            val s = viewModel.seconds.value?.toInt() ?: 10
            findNavController().navigate(SelectGameFragmentDirections.actionNavSelectGameToNavGame(s))
        }
    }
}

/**
 * フィルター機能のないフィルター。筒やストローのようなもの。
 *　AutoCompleteTextView で常に、すべての候補を表示する
 */
class NoFilterArrayAdapter<T>(context: Context, resource: Int, objects: Array<T>) :
    ArrayAdapter<T>(context, resource, objects) {

    private val filter = object : Filter() {
        // フィルター処理をせず、Adapterに入ってきた入力データをFilterResults型でそのまま返す。
        override fun performFiltering(constraint: CharSequence?) =
            FilterResults().apply {
                values = objects
                count = objects.size
            }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return filter
    }
}
