package com.wel.randomuser.remote.mappers

interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E
}
