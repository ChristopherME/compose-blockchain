package com.christopherelias.blockchain.utils.connectivity

/*
 * Created by Christopher Elias on 11/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface ConnectivityUtils {
    /**
     * @return TRUE if client is connected to Wife or Cell data.
     */
    fun isNetworkAvailable(): Boolean
}