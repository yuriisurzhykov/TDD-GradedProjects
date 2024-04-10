package com.yuriisurzhykov.tddgraded.core.domain

interface Mapper<T, S> {

    fun map(from: T): S

    abstract class UnitMapper<T> : Mapper<T, Unit>

}