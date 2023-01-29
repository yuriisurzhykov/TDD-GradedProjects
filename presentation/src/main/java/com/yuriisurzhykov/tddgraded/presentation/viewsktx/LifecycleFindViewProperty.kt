package com.yuriisurzhykov.tddgraded.presentation.viewsktx

import android.view.View

abstract class LifecycleFindViewProperty<in T : Any, out V : View> constructor(
    onViewDestroyed: (V) -> Unit,
    viewMapper: (T) -> V
) : LifecycleReadOnlyProperty<T, V>(viewMapper, onViewDestroyed)

