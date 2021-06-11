package com.christopherelias.blockchain

import com.christopherelias.blockchain.core.network.middleware.provider.MiddlewareProvider
import com.christopherelias.blockchain.core.network.models.ResponseError
import com.christopherelias.blockchain.core.network.models.ResponseList
import com.christopherelias.blockchain.core.network.utils.ServiceBodyFailure
import com.christopherelias.blockchain.features.home.data.data_source.HomeRemoteDataSource
import com.christopherelias.blockchain.features.home.data.repository.HomeRepositoryImpl
import com.christopherelias.blockchain.features.home.data_source.model.TransactionPerSecondResponse
import com.christopherelias.blockchain.features.home.data_source.remote.HomeRemoteDataSourceImpl
import com.christopherelias.blockchain.features.home.data_source.remote.HomeService
import com.christopherelias.blockchain.features.home.domain.repository.HomeRepository
import com.christopherelias.blockchain.features.home.mapper.TransactionMapper
import com.christopherelias.blockchain.features.home.mapper.TransactionMapperImpl
import com.christopherelias.blockchain.utils.DefaultTestNetworkMiddleware
import com.christopherelias.blockchain.utils.TransactionsData
import com.christopherelias.blockchain.utils.getDataWhenResultIsFailureOrThrowException
import com.christopherelias.blockchain.utils.getDataWhenResultIsSuccessOrThrowException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@ExperimentalCoroutinesApi
class HomeRepositoryUnitTest {

    private val moshi = Moshi.Builder().build()

    private val errorAdapter: JsonAdapter<ResponseError> =
        moshi.adapter(ResponseError::class.java)

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val homeService = mockk<HomeService>()

    private val middlewareProvider = mockk<MiddlewareProvider>()

    private val remoteDataSource: HomeRemoteDataSource = HomeRemoteDataSourceImpl(
        middlewareProvider = middlewareProvider,
        ioDispatcher = testCoroutineDispatcher,
        errorAdapter = errorAdapter,
        homeService = homeService
    )

    private val mapper: TransactionMapper = TransactionMapperImpl(testCoroutineDispatcher)

    private val homeRepository: HomeRepository = HomeRepositoryImpl(
        mapper = mapper,
        homeRemoteDataSource = remoteDataSource
    )


    @Test
    fun `Assert repository return transaction rate values when remote service works as expected`() {
        // Load data from resources. This is not so "fake",
        // it's the actual response from the Blockchain API.
        val remoteTransactions: List<TransactionPerSecondResponse> =
            TransactionsData.provideRemoteTransactionsFromAssets()

        // Bypass all middlewares
        every { middlewareProvider.getAll() } returns listOf(
            DefaultTestNetworkMiddleware(
                isMiddlewareValid = true
            )
        )

        // Mock homeService response
        coEvery {
            homeService.getTransactionsPerSecond(
                chartName = any(),
                timeSpan = any(),
                rollingAverage = any()
            )
        } returns ResponseList(remoteTransactions)

        runBlockingTest {

            // TODO: Use async here for find the max rate value from remote while getting the repository data.

            val remoteMaxTransactionRateValue: Double = remoteTransactions.findMaxRateValue()

            homeRepository.getTransactionsPerSecond()
                .getDataWhenResultIsSuccessOrThrowException { transactionUi ->
                    assertEquals(
                        "Remote transactions list size doesn't match with the ones returned by the repository.",
                        remoteTransactions.size,
                        transactionUi.transactions.size
                    )
                    assertEquals(
                        "Max transaction rate value from remote does not match the one returned by the repository",
                        remoteMaxTransactionRateValue,
                        transactionUi.maxTransaction,
                        0.001
                    )
                }
        }
    }

    @Test
    fun `Assert repository return network service call exception properly`() {
        val errorBody = "{\"status\": \"Invalid Request\",\"error\": \"Error\"}"
            .toResponseBody("application/json".toMediaTypeOrNull())

        every { middlewareProvider.getAll() } returns listOf(
            DefaultTestNetworkMiddleware(
                isMiddlewareValid = true
            )
        )

        coEvery {
            homeService.getTransactionsPerSecond(any(), any(), any())
        } throws HttpException(Response.error<Any>(400, errorBody))

        runBlockingTest {
            homeRepository.getTransactionsPerSecond()
                .getDataWhenResultIsFailureOrThrowException { failure ->
                    assertEquals(
                        ServiceBodyFailure(
                            internalStatus = "Invalid Request",
                            internalMessage = "Error"
                        ),
                        failure
                    )
                }
        }
    }

    private suspend fun List<TransactionPerSecondResponse>.findMaxRateValue(): Double {
        return withContext(testCoroutineDispatcher) {
            return@withContext maxByOrNull { it.transactionsPerSecondValue }
                ?.transactionsPerSecondValue ?: 0.0
        }
    }
}