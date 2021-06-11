package com.christopherelias.blockchain.utils.resource_provider

/*
 * Created by Christopher Elias on 11/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

interface ResourceProvider {
    fun getString(resourceId: Int): String
    fun getString(resourceId: Int, vararg args: Any): String
}