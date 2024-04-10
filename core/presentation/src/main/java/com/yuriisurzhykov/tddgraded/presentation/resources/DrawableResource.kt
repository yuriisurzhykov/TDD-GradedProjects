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

package com.yuriisurzhykov.tddgraded.presentation.resources

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

interface DrawableResource : IResourceManager<Drawable?> {
    open class Base : DrawableResource {
        override fun getResource(id: Int, context: Context): Drawable? {
            return ContextCompat.getDrawable(context, id)
        }
    }
}