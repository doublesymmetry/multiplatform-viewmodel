package com.doublesymmetry.viewmodel

import kotlinx.atomicfu.locks.reentrantLock
import kotlinx.atomicfu.locks.withLock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

actual open class ViewModel {
    private val lock = reentrantLock()
    private var backingScope: CoroutineScope? = null

    actual val scope: CoroutineScope
        get() {
            return this.getScopeInstance() ?: this.createScopeInstance()
        }

    fun clear() {
        lock.withLock {
            closeWithRuntimeException(backingScope)
            backingScope = null
        }
    }

    private fun getScopeInstance(): CoroutineScope? {
        lock.withLock { return backingScope }
    }

    private fun createScopeInstance(): CoroutineScope {
        val scope = CloseableCoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
        backingScope = scope
        return scope
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
