package com.github.dev001hajipro.notorekraepelin.ui.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.dev001hajipro.notorekraepelin.getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: GameViewModel

    @Before
    fun setUp() {
        viewModel = GameViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun setInitValues_shouldGetDefaultValues() {
        // When
        viewModel.init()
        // Then
        assertThat(viewModel.secondsUntilFinished.value, `is`(60))
        assertThat(viewModel.elapsedSeconds.value, `is`(0))
        // LiveDataは監視してから動作するので、observeForeverで監視して結果を待機。
        assertThat(viewModel.remainingSeconds.getOrAwaitValue(), `is`(60))
    }

    @Test
    fun setInit180_shouldGetSameValue() {
        // When
        viewModel.init(seconds = 180)
        // Then
        assertThat(viewModel.secondsUntilFinished.value, `is`(180))
        assertThat(viewModel.elapsedSeconds.value, `is`(0))
        // LiveDataは監視してから動作するので、observeForeverで監視して結果を待機。
        assertThat(viewModel.remainingSeconds.getOrAwaitValue(), `is`(180))
    }

    @Test
    fun x() {
        // When
        viewModel.init()

        // Then
    }
}
