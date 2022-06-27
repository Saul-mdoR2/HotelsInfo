package com.r2devpros.hotelsinfo.repository.remote

inline fun<T> Pretty<T>.handle(
    crossinline success: (T) -> Unit = {},
    crossinline error: (Throwable) -> Unit = {}
) {
    when(this) {
        is Pretty.Success -> success(this.value)
        is Pretty.Error -> error(this.throwable)
    }
}