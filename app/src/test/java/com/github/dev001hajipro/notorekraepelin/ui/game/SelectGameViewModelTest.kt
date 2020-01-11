package com.github.dev001hajipro.notorekraepelin.ui.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SelectGameViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SelectGameViewModel

    @Before
    fun setupViewModel() {
        viewModel = SelectGameViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun callInit_SetSeconds() {
        // When call init
        viewModel.init(seconds = 180)

        // Then set seconds variable.
        assertThat(viewModel.seconds.value, `is`(180))
    }

}