package com.example.cryptoexchangedemo.domain.util

/**
 * Created by Batuhan Duvarci on 27.11.2021.
 */
interface DomainMapper<T, DomainModel> {

    fun mapToDomainModel(response: T): DomainModel

    fun fromEntityList(initial: List<T>): List<DomainModel>
}