/*
 * Copyright (c) 2023 Yurii Surzhykov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yuriisurzhykov.tddgraded.presentation.delegates.viewsktx

import android.os.Handler
import android.os.Looper
import androidx.annotation.CallSuper
import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlin.reflect.KProperty

abstract class LifecycleReadOnlyProperty<in R : Any, out T : Any> constructor(
    private val valueBinder: (R) -> T,
    private val onViewDestroyed: (T) -> Unit
) : ReadOnlyClearProperty<R, T> {

    private var view: T? = null

    protected abstract fun getLifecycleOwner(thisRef: R): LifecycleOwner

    @MainThread
    override fun getValue(thisRef: R, property: KProperty<*>): T {
        checkMainThread()
        view?.let { return it }

        if (!isViewInitialized(thisRef)) {
            error(viewNotInitializedReadableErrorMessage(thisRef))
        }

        runStrictModeChecks(thisRef)

        val lifecycle = getLifecycleOwner(thisRef).lifecycle

        return if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            view = null
            valueBinder.invoke(thisRef)
        } else {
            val view = valueBinder.invoke(thisRef)
            lifecycle.addObserver(ClearOnDestroyLifecycleObserver(this))
            this.view = view
            view
        }
    }

    private fun viewNotInitializedReadableErrorMessage(thisRef: R): String {
        return "View $thisRef must be initialized before getting value!"
    }

    @CallSuper
    @MainThread
    override fun clear() {
        checkMainThread()
        val view = view
        this.view = null
        if (view != null) {
            onViewDestroyed(view)
        }
    }

    open fun postClear() {
        if (!mainHandler.post { clear() }) {
            clear()
        }
    }

    protected open fun isViewInitialized(thisRef: R): Boolean = true

    private fun runStrictModeChecks(thisRef: R) {
        val lifecycle = getLifecycleOwner(thisRef).lifecycle
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            error(ERROR_ACCESS_AFTER_DESTROY)
        }
    }

    private class ClearOnDestroyLifecycleObserver(
        private val property: LifecycleReadOnlyProperty<*, *>
    ) : DefaultLifecycleObserver {

        override fun onCreate(owner: LifecycleOwner) {
            // Do nothing
        }

        override fun onStart(owner: LifecycleOwner) {
            // Do nothing
        }

        override fun onResume(owner: LifecycleOwner) {
            // Do nothing
        }

        override fun onPause(owner: LifecycleOwner) {
            // Do nothing
        }

        override fun onStop(owner: LifecycleOwner) {
            // Do nothing
        }

        @MainThread
        override fun onDestroy(owner: LifecycleOwner) {
            property.postClear()
        }
    }

    private companion object {
        private val mainHandler = Handler(Looper.getMainLooper())
        private const val ERROR_ACCESS_AFTER_DESTROY = "Cannot access for view after owner was destroyed!"
    }
}