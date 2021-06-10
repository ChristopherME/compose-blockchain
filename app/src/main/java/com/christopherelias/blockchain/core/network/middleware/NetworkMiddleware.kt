package com.christopherelias.blockchain.core.network.middleware

import com.christopherelias.blockchain.core.network.models.NetworkMiddlewareFailure

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

abstract class NetworkMiddleware {

    abstract val failure: NetworkMiddlewareFailure

    abstract fun isValid(): Boolean
}