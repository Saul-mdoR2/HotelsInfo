package com.r2devpros.hotelsinfo.repository.remote

import retrofit2.Response

/**
 * Decodes a Retrofit [Response], translates a possible error into an [Exception]
 */
fun <I, O> Response<I>.decodeResponse(onSuccess: (I) -> O): O =
    when {
        isSuccessful -> body()?.let {
            onSuccess(it)
        }
        else -> throw Exception(errorBody()?.string())
    } ?: throw RuntimeException("Error executing API call")

fun <T> Result<T>.toPretty(): Pretty<T> {
    return if (isSuccess) {
        Pretty.Success(this.getOrNull()!!)
    } else {
        Pretty.Error(this.exceptionOrNull()!!)
    }
}