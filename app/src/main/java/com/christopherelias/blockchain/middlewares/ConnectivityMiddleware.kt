package com.christopherelias.blockchain.middlewares

import com.christopherelias.blockchain.R
import com.christopherelias.blockchain.core.network.middleware.NetworkMiddleware
import com.christopherelias.blockchain.core.network.middleware.NetworkMiddlewareFailure
import com.christopherelias.blockchain.utils.connectivity.ConnectivityUtils
import com.christopherelias.blockchain.utils.resource_provider.ResourceProvider
import javax.inject.Inject

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */


class ConnectivityMiddleware @Inject constructor(
    private val connectivityUtils: ConnectivityUtils,
    private val resourceProvider: ResourceProvider
) : NetworkMiddleware() {

    override val failure: NetworkMiddlewareFailure
        get() = NetworkMiddlewareFailure(
            middleWareExceptionMessage = resourceProvider.getString(R.string.error_no_network_detected)
        )

    override fun isValid(): Boolean = connectivityUtils.isNetworkAvailable()
}