package com.github.dev001hajipro.notorekraepelin.ui.game

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class GameResultViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: GameResultViewModel

    @Before
    fun setUp() {
        // Given
        viewModel = GameResultViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun setInitValues_shouldGetSameValues() {
        // When
        viewModel.init(10, 10, 10f)

        // Then
        viewModel.numberOfCorrectAnswers.value?.let {
            assertThat(it, `is`(10))
        }
        viewModel.numberOfIncorrectAnswers.value?.let {
            assertThat(it, `is`(10))
        }
        viewModel.grade.value?.let {
            assertThat(it, `is`(10f))
        }
    }
}
