package com.yuriisurzhykov.tddgraded.presentation

import androidx.fragment.app.Fragment

interface IFragmentActivity {
    fun openFragment(fragment: Fragment, tag: Any)
    fun openFragment(fragment: Fragment, tag: Any, returnToExists: Boolean)
}