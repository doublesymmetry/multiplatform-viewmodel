package com.doublesymmetry.viewmodel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ViewModelTest {

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun lazyViewModelScopeIsThreadSafe() = runTest {
        val testViewModel =  object : ViewModel() {}
        val scope = testViewModel.viewModelScope
        val scope2 = testViewModel.viewModelScope

        assertNotNull(scope)
        assertNotNull(scope2)
        assertEquals(scope, scope2)

        launch {
            val scope3 = testViewModel.viewModelScope
            assertNotNull(scope3)
            assertEquals(scope, scope3)
        }
    }
}