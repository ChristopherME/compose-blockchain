package com.christopherelias.blockchain.features.home.data_source.remote

import com.christopherelias.blockchain.core.functional_programming.Either
import com.christopherelias.blockchain.core.functional_programming.Failure
import com.christopherelias.blockchain.core.network.middleware.provider.MiddlewareProvider
import com.christopherelias.blockchain.core.network.models.ResponseError
import com.christopherelias.blockchain.core.network.utils.call
import com.christopherelias.blockchain.di.IoDispatcher
import com.christopherelias.blockchain.features.home.data.data_source.TransactionsRemoteDataSource
import com.christopherelias.blockchain.features.home.data_source.model.TransactionPerSecondResponse
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class TransactionsRemoteDataSourceImpl @Inject constructor(
    private val middlewareProvider: MiddlewareProvider,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val errorAdapter: JsonAdapter<ResponseError>,
    private val transactionsService: TransactionsService
) : TransactionsRemoteDataSource {

    override suspend fun getTransactionsPerSecond(): Either<Failure, List<TransactionPerSecondResponse>> {
        return call(
            middleWares = middlewareProvider.getAll(),
            ioDispatcher = ioDispatcher,
            adapter = errorAdapter,
            retrofitCall = {
                transactionsService.getTransactionsPerSecond(
                    chartName = "transactions-per-second",
                    timeSpan = "5weeks",
                    rollingAverage = "8hours"
                )
            }
        ).let { response -> response.mapSuccess { it.items } }
    }
}