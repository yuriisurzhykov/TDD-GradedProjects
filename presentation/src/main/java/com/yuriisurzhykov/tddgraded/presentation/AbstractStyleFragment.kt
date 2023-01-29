package com.yuriisurzhykov.tddgraded.presentation

import androidx.fragment.app.Fragment

abstract class AbstractStyleFragment : Fragment, IStyleFragment {
    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)
}