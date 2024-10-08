package com.wel.randomuser.cache.mapper

interface CacheMapper<T, V> {
    fun mapFromCached(type: T): V
    fun mapToCached(type: V): T
}
