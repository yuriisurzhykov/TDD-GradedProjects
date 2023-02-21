package com.yuriisurzhykov.testingcore

import com.yuriisurzhykov.tddgraded.core.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class FakeDispatchers :
    Dispatchers.Abstract(UnconfinedTestDispatcher(), UnconfinedTestDispatcher()) {}