package com.doublesymmetry.viewmodel

import kotlinx.coroutines.CoroutineScope

expect open class ViewModel() {
    val scope: CoroutineScope
}
