package com.yuriisurzhykov.tddgraded.tictactoe.core.presentation

import android.os.Bundle
import com.yuriisurzhykov.tddgraded.presentation.AbstractFragmentActivity

class ActivityTicTacToe : AbstractFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFragment(FragmentMainMenu(), "main_menu")
    }
}