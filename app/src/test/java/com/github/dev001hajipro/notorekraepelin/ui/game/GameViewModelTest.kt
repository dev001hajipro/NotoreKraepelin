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
        assertThat(viewModel.millisecondsUntilFinished.value, `is`(60_000L))
        assertThat(viewModel.elapsedMilliSeconds.value, `is`(0L))
        assertThat(viewModel.remainingMilliseconds.getOrAwaitValue(), `is`(60_000L))
    }

    @Test
    fun setInit180_shouldGetSameValue() {
        // When
        viewModel.init(milliseconds = 180_000L)
        // Then
        assertThat(viewModel.millisecondsUntilFinished.value, `is`(180_000L))
        assertThat(viewModel.elapsedMilliSeconds.value, `is`(0L))
        assertThat(viewModel.remainingMilliseconds.getOrAwaitValue(), `is`(180_000L))
    }

    @Test
    fun x() {
        // When
        viewModel.init()

        // Then
    }
}
