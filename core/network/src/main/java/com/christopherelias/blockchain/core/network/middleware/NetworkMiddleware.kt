package com.christopherelias.blockchain.core.network.middleware

/*
 * Created by Christopher Elias on 11/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

abstract class NetworkMiddleware {

    abstract val failure: NetworkMiddlewareFailure

    abstract fun isValid(): Boolean
}