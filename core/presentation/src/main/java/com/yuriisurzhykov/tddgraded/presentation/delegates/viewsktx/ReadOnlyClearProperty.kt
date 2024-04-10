package com.yuriisurzhykov.tddgraded.presentation.delegates.viewsktx

import kotlin.properties.ReadOnlyProperty

interface ReadOnlyClearProperty<in T : Any, out V : Any> : ReadOnlyProperty<T, V> {

    fun clear()
}