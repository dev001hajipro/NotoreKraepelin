package com.github.dev001hajipro.notorekraepelin.ui.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
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
        assertThat(viewModel.elapsedSeconds.value, `is`(0))
    }
}
