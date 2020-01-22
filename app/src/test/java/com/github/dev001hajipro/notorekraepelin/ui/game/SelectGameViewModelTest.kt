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
class SelectGameViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SelectGameViewModel

    @Before
    fun setUp() {
        // Given
        viewModel = SelectGameViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun initSeconds180_shouldSetSeconds() {
        // When call init with arg
        viewModel.init(seconds = 180)

        // Then set seconds variable.
        assertThat(viewModel.seconds.value, `is`(180))
    }

    @Test
    fun initNoArgs_shouldSetSecondsDefaultValue() {
        viewModel.init()
        // TODO delete magic number.
        val expected = 60
        assertThat(viewModel.seconds.value, `is`(expected))
    }

}
