package com.christopherelias.blockchain.utils

import com.christopherelias.blockchain.core.network.models.ResponseList
import com.christopherelias.blockchain.features.home.data_source.model.TransactionPerSecondResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

object TransactionsData {

    private val moshi = Moshi.Builder().build()
    private val transactionsResponseGenericType: Type = Types.newParameterizedType(
        ResponseList::class.java,
        TransactionPerSecondResponse::class.java
    )
    private val remoteTransactionsAdapter: JsonAdapter<ResponseList<TransactionPerSecondResponse>> =
        moshi.adapter(transactionsResponseGenericType)

    fun provideRemoteTransactionsFromAssets(): List<TransactionPerSecondResponse> {
        return remoteTransactionsAdapter.fromJson(
            FileReaderUtil.kotlinReadFileWithNewLineFromResources(
                fileName = "transactions_response.json"
            )
        )?.items ?: listOf()
    }
}