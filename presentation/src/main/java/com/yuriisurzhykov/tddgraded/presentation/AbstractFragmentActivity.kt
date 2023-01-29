package com.yuriisurzhykov.tddgraded.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class AbstractFragmentActivity : AbstractToolbarActivity(), IFragmentActivity {

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