package com.christopherelias.blockchain.utils

import com.christopherelias.blockchain.core.network.middleware.NetworkMiddleware
import com.christopherelias.blockchain.core.network.middleware.NetworkMiddlewareFailure

/*
 * Created by Christopher Elias on 10/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class DefaultTestNetworkMiddleware(
    private val isMiddlewareValid: Boolean,
    private val failureMessage: String = ""
) : NetworkMiddleware() {

    override val failure: NetworkMiddlewareFailure
        get() = NetworkMiddlewareFailure(middleWareExceptionMessage = failureMessage)

    override fun isValid(): Boolean = isMiddlewareValid

}