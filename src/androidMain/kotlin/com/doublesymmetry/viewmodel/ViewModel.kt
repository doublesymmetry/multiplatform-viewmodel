package com.doublesymmetry.viewmodel

import androidx.lifecycle.ViewModel as AndroidXViewModel
import androidx.lifecycle.viewModelScope

actual open class ViewModel : AndroidXViewModel() {
    actual val scope = viewModelScope
}
