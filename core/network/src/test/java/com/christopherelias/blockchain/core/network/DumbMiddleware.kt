package com.christopherelias.blockchain.core.network

import com.christopherelias.blockchain.core.network.middleware.NetworkMiddleware
import com.christopherelias.blockchain.core.network.middleware.NetworkMiddlewareFailure

/*
 * Created by Christopher Elias on 11/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class DumbMiddleware(
    private val hardCodedValidation: Boolean,
    private val middlewareFailureMessage: String
) : NetworkMiddleware() {

    override val failure: NetworkMiddlewareFailure
        get() = NetworkMiddlewareFailure(middleWareExceptionMessage = middlewareFailureMessage)

    override fun isValid(): Boolean = hardCodedValidation
}

class AnotherDumbMiddleware() : NetworkMiddleware() {
    override val failure: NetworkMiddlewareFailure
        get() = NetworkMiddlewareFailure("")

    override fun isValid(): Boolean = true

}