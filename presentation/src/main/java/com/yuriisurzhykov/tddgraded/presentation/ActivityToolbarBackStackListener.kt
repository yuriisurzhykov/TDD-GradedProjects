package com.yuriisurzhykov.tddgraded.presentation

import androidx.fragment.app.FragmentManager

open class ActivityToolbarBackStackListener constructor(
    private val fragmentResource: IActivityToolbarSource
) : FragmentManager.OnBackStackChangedListener {
    override fun onBackStackChanged() {
        (fragmentResource.getActiveFragment() as? IStyleFragment)?.let { fragment ->
            fragmentResource.getSupportActionBar()?.title = fragment.getTitle()
        }
    }
}