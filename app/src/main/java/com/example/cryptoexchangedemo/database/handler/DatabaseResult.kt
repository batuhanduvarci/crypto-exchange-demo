package com.example.cryptoexchangedemo.database.handler

sealed class DatabaseResult(
    val message: String? = null
){
    class Success(message: String?): DatabaseResult(message)

    class Error(message: String?): DatabaseResult(message)

    class Loading: DatabaseResult()
}
