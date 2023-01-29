package com.yuriisurzhykov.tddgraded.core

interface SuspendMapper<T, S> {

    suspend fun map(from: T): S

    abstract class UnitMapper<T> : Mapper<T, Unit>

}