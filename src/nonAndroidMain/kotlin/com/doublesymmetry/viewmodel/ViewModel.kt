package com.doublesymmetry.viewmodel

import kotlinx.coroutines.*

actual abstract class ViewModel {
    private var hasCleared = false

    actual val viewModelScope: CoroutineScope by lazy {
        val result = CloseableCoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

        if (hasCleared)
            closeWithRuntimeException(result)

        return@lazy result
    }

    protected actual open fun onCleared() {}

    /**
     * Closes the [viewModelScope] and cancels all its coroutines.
     * Should be called from main thread.
     */
    fun clear() {
        hasCleared = true
        closeWithRuntimeException(viewModelScope)
        onCleared()
    }

    companion object {
        private fun closeWithRuntimeException(obj: Any?) {
            if (obj is Closeable) {
                try {
                    obj.close()
                } catch (e: Exception) {
                    throw RuntimeException(e)
                }
            }
        }
    }
}
