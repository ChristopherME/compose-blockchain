package com.christopherelias.blockchain.middlewares

import com.christopherelias.blockchain.core.network.middleware.NetworkMiddleware
import com.christopherelias.blockchain.core.network.middleware.provider.MiddlewareProvider

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class MiddlewareProviderImpl private constructor(
    private val middlewareList: List<NetworkMiddleware> = listOf()
) : MiddlewareProvider {

    class Builder(
        private val middlewareList: MutableList<NetworkMiddleware> = mutableListOf()
    ) {

        fun add(middleware: NetworkMiddleware) = apply {
            this.middlewareList.add(middleware)
        }

        fun build() = MiddlewareProviderImpl(
            middlewareList = middlewareList
        )
    }


    override fun getAll(): List<NetworkMiddleware> = middlewareList
}