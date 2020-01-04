package com.github.dev001hajipro.notorekraepelin.ui.anime


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.vectordrawable.graphics.drawable.AnimatorInflaterCompat
import com.github.dev001hajipro.notorekraepelin.R
import com.github.dev001hajipro.notorekraepelin.databinding.FragmentAnimeBinding
import kotlinx.android.synthetic.main.fragment_anime.*

/**
 * A simple [Fragment] subclass.
 */
class AnimeFragment : Fragment() {

    private val logTag = AnimeFragment::class.java.simpleName

    private val viewModel: AnimeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentAnimeBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // キーバッドを押したときのアニメ
        button1.setOnClickListener {
            Log.d(logTag, "onclick")
            // DrawableAnimation アイコンなどビットマップをアニメーションするときに使う
            // Viewの移動、表示、非表示はプロパティ アニメーションを使う。
            /*
            val a1 = ObjectAnimator.ofFloat(text2, "textSize", 24f, 48f, 24f).apply {
                duration = 250
            }
            val a2 = ObjectAnimator.ofFloat(text2, "alpha", 0.8f, 1f).apply {
                duration = 250
            }
            AnimatorSet().apply {
                play(a1)
                play(a2)
            }.start()

             */


            arrayOf(text2).forEach {
                AnimatorInflaterCompat.loadAnimator(
                    this@AnimeFragment.context,
                    R.animator.show_numbers
                ).apply {
                    setTarget(it)
                }.start()
            }

        }

        button2.setOnClickListener {
            arrayOf(text2, text3, text4).forEach {
                AnimatorInflaterCompat.loadAnimator(
                    this@AnimeFragment.context,
                    R.animator.show_numbers
                ).apply {
                    setTarget(it)
                }.start()
            }
        }
        // hide anime
        button3.setOnClickListener {
            /*
            AnimatorInflaterCompat.loadAnimator(this@AnimeFragment.context, R.animator.hide_numbers).apply {
                setTarget(text2)
            }.start()
             */
            arrayOf(text2, text3, text4).forEach {
                AnimatorInflaterCompat.loadAnimator(
                    this@AnimeFragment.context,
                    R.animator.hide_numbers
                ).apply {
                    setTarget(it)
                }.start()
            }

            /*
            val s1 = ObjectAnimator.ofFloat(text2, "textSize", 24f, 48f).apply {
                duration = 250
            }
            val s2 = ObjectAnimator.ofFloat(text3, "textSize", 24f, 48f).apply {
                duration = 250
            }
            val s3 = ObjectAnimator.ofFloat(text4, "textSize", 24f, 48f).apply {
                duration = 250
            }

            val a1 = ObjectAnimator.ofFloat(text2, "alpha", 1f, 0f).apply {
                duration = 250
            }
            val a2 = ObjectAnimator.ofFloat(text3, "alpha", 1f, 0f).apply {
                duration = 250
            }
            val a3 = ObjectAnimator.ofFloat(text4, "alpha", 1f, 0f).apply {
                duration = 250
            }
            AnimatorSet().apply {
                play(a1).with(s1)
                play(a2).with(s2)
                play(a3).with(s3)

            }.start()
             */
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
