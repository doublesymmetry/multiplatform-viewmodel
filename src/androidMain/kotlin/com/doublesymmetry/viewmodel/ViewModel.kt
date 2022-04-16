package com.doublesymmetry.viewmodel

import androidx.lifecycle.ViewModel as AndroidXViewModel
import androidx.lifecycle.viewModelScope as androidXViewModelScope

actual open class ViewModel : AndroidXViewModel() {
    actual val viewModelScope = androidXViewModelScope
}
