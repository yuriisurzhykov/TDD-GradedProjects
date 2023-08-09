package com.yuriisurzhykov.tddgraded.presentation

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class AbstractFragmentActivity : AbstractToolbarActivity(), IFragmentActivity {

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (supportFragmentManager.backStackEntryCount == 1) {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, backPressedCallback)
    }

    override fun openFragment(fragment: Fragment, tag: Any) {
        openFragment(fragment, tag, false)
    }

    override fun openFragment(fragment: Fragment, tag: Any, returnToExists: Boolean) {
        val fm = supportFragmentManager
        val findFragment: Fragment? = fm.findFragmentByTag(tag.toString())
        if (findFragment != null && returnToExists) {
            fm.popBackStack(tag.toString(), FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } else {
            fm.beginTransaction()
                .replace(R.id.fragment_container, fragment, tag.toString())
                .addToBackStack(tag.toString())
                .commit()
        }
    }
}