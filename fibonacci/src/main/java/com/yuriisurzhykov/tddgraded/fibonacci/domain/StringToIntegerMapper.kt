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

package com.yuriisurzhykov.tddgraded.fibonacci.domain

import com.yuriisurzhykov.tddgraded.core.Mapper
import com.yuriisurzhykov.tddgraded.fibonacci.domain.error.InvalidNumberException

interface StringToIntegerMapper : Mapper<String, Int> {
    class Base : StringToIntegerMapper {
        override fun map(from: String): Int {
            val modified = from.replace(" ", "").split(".").first()
            if (modified.isBlank() || modified.isEmpty()) {
                throw IllegalArgumentException("String is empty")
            }
            return try {
                modified.toInt()
            } catch (e: NumberFormatException) {
                throw InvalidNumberException(from)
            }
        }
    }
}
