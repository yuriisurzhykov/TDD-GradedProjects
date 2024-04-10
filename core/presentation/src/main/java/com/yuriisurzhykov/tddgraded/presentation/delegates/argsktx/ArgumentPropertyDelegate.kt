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

package com.yuriisurzhykov.tddgraded.presentation.delegates.argsktx

import android.os.Build
import androidx.fragment.app.Fragment
import com.yuriisurzhykov.tddgraded.presentation.delegates.viewsktx.FragmentReadOnlyPropertyDelegate
import java.io.Serializable
import kotlin.properties.ReadOnlyProperty

fun <F : Fragment> F.stringArgument(argName: String): ReadOnlyProperty<F, String> {
    return fragmentArguments({ requireArguments().getString(argName).orEmpty() })
}

fun <F : Fragment> F.longArgument(argName: String): ReadOnlyProperty<F, Long> {
    return fragmentArguments({ requireArguments().getLong(argName) })
}

fun <F : Fragment> F.doubleArgument(argName: String): ReadOnlyProperty<F, Double> {
    return fragmentArguments({ requireArguments().getDouble(argName) })
}

@Suppress("DEPRECATION")
inline fun <F : Fragment, reified T : Any> F.parcelableArgument(argName: String): ReadOnlyProperty<F, T> {
    return fragmentArguments({
        if (Build.VERSION.SDK_INT >= 33) {
            requireArguments().getParcelable(argName, T::class.java)!!
        } else {
            requireArguments().getParcelable(argName)!!
        }
    })
}

@Suppress("DEPRECATION")
inline fun <F : Fragment, reified T : Serializable> F.serializableArgument(argName: String): ReadOnlyProperty<F, T> {
    return fragmentArguments({
        if (Build.VERSION.SDK_INT >= 33) {
            requireArguments().getSerializable(argName, T::class.java)!!
        } else {
            requireArguments().getSerializable(argName) as T
        }
    })
}

fun <F : Fragment, T : Any> F.fragmentArguments(
    valueMapper: (Fragment) -> T,
    onDestroyView: (T) -> Unit = {}
): ReadOnlyProperty<F, T> {
    return ArgumentPropertyDelegate(valueMapper, onDestroyView)
}

inline fun <F : Fragment, reified T : Any> F.fragmentArguments(
    argName: String
): ReadOnlyProperty<F, T> {
    return ArgumentPropertyDelegate({
        requireArguments().get(argName) as T
    }, {})
}

class ArgumentPropertyDelegate<T : Any>(
    valueMapper: (Fragment) -> T,
    onDestroyView: (T) -> Unit
) : FragmentReadOnlyPropertyDelegate<Fragment, T>(
    viewNeedInitialization = false,
    valueMapper,
    onDestroyView
)