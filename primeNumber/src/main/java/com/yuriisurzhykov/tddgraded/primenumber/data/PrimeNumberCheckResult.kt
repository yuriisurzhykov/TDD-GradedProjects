package com.yuriisurzhykov.tddgraded.primenumber.data

import android.widget.TextView
import androidx.annotation.StringRes
import com.yuriisurzhykov.tddgraded.primenumber.R

interface PrimeNumberCheckResult {

    fun applyToTextView(textView: TextView)

    abstract class Abstract(@StringRes private val stringRes: Int) : PrimeNumberCheckResult {
        override fun equals(other: Any?): Boolean {
            if (other == null) return false
            return this::class.java == other::class.java
        }

        override fun hashCode(): Int {
            return javaClass.hashCode()
        }

        override fun applyToTextView(textView: TextView) {
            textView.setText(stringRes)
        }
    }

    class Prime : Abstract(R.string.label_number_is_prime)
    class NotPrime : Abstract(R.string.label_number_is_not_prime)
    class InvalidRange : Abstract(R.string.label_number_invalid)
}