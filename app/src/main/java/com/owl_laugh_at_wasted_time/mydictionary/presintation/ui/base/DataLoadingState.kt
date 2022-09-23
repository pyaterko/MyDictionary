package com.owl_laugh_at_wasted_time.mydictionary.presintation.ui.base

typealias Mapper<Input, Output> = (Input) -> Output

sealed class DataLoadingState<T> {

    fun <R> map(mapper: Mapper<T, R>? = null): DataLoadingState<R> = when (this) {
        is Loading -> Loading()
        is Error -> Error(this.exception)
        is Success -> {
            if (mapper == null) throw IllegalArgumentException("Mapper should not be NULL for success result")
            Success(mapper(this.data))
        }
    }
}


class Loading<T> : DataLoadingState<T>()

class Success<T>(
    val data: T
) : DataLoadingState<T>()

class Error<T>(
    val exception: Exception
) : DataLoadingState<T>()

