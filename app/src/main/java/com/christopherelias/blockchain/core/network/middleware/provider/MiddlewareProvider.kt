package com.christopherelias.blockchain.core.network.middleware.provider

import com.christopherelias.blockchain.core.network.middleware.NetworkMiddleware

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface MiddlewareProvider {
    fun getAll(): List<NetworkMiddleware>
}