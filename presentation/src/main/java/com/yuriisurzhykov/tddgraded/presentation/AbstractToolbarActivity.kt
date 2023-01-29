package com.yuriisurzhykov.tddgraded.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractToolbarActivity : AppCompatActivity() {

    private val activityToolbarSource: IActivityToolbarSource = object : IActivityToolbarSource {
        override fun getActiveFragment() = supportFragmentManager.findFragmentById(R.id.fragment_container)

        override fun getSupportActionBar() = this@AbstractToolbarActivity.supportActionBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportFragmentManager.addOnBackStackChangedListener(
            ActivityToolbarBackStackListener(activityToolbarSource)
        )
    }
}