package com.yuriisurzhykov.tddgraded.presentation

import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment

interface IActivityToolbarSource {
    fun getActiveFragment(): Fragment?
    fun getSupportActionBar(): ActionBar?
}