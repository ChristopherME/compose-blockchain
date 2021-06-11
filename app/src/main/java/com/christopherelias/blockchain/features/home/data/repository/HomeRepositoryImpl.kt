package com.christopherelias.blockchain.features.home.data.repository

import com.christopherelias.blockchain.functional_programming.Either
import com.christopherelias.blockchain.functional_programming.Failure
import com.christopherelias.blockchain.features.home.data.data_source.HomeRemoteDataSource
import com.christopherelias.blockchain.features.home.domain.repository.HomeRepository
import com.christopherelias.blockchain.features.home.mapper.TransactionMapper
import com.christopherelias.blockchain.ui.models.TransactionsPerSecond
import javax.inject.Inject

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class HomeRepositoryImpl @Inject constructor(
    private val mapper: TransactionMapper,
    private val homeRemoteDataSource: HomeRemoteDataSource
) : HomeRepository {

    override suspend fun getTransactionsPerSecond(): Either<Failure, TransactionsPerSecond> {
        return homeRemoteDataSource.getTransactionsPerSecond()
            .coMapSuccess { response -> mapper.mapRemoteTransactionsToUi(response) }
    }
}