package com.yuriisurzhykov.tddgraded.core.domain

interface SuspendMapper<T, S> {

    suspend fun map(from: T): S

    abstract class UnitMapper<T> : Mapper<T, Unit>

}