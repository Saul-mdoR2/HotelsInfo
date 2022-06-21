package com.r2devpros.hotelsinfo.repository.remote

/**
 * Sealed class to handle success and error states.
 * <p> This class is based on Kotlin's [Result] to be used for HTTP calls but it can also be use elsewhere </p>
 */
sealed class Pretty<out T> {

    abstract val success: Boolean

    /**
     * Success [Pretty] class. Encapsulates the value of a successful HTTP call.
     *
     * @property success Always true.
     * @param value Resulting value of an HTTP call.
     */
    class Success<out T>(val value: T) : Pretty<T>() {
        override val success: Boolean
            get() = true
    }

    /**
     * Error [Pretty] class. Encapsulates the error of an HTTP call into a Throwable
     *
     * @property success Always false.
     * @param throwable Throwable that represents the error state of an HTTP call.
     */
    class Error<out T>(val throwable: Throwable) : Pretty<T>() {
        override val success: Boolean
            get() = false
    }
}