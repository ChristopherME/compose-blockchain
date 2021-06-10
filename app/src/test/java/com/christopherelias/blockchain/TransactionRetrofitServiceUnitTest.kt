package com.christopherelias.blockchain

import com.christopherelias.blockchain.features.home.data_source.remote.TransactionsService
import com.christopherelias.blockchain.utils.FileReaderUtil
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/*
 * Created by Christopher Elias on 9/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

//This will test if our data classes are well mapped with the expected response.
class TransactionRetrofitServiceUnitTest {

    private val chartName = "transactions-per-second"
    private val timeSpan = "5weeks"
    private val rollingAverage = "8hours"

    // Mock web server
    private val mockWebServer = MockWebServer()
    private lateinit var transactionService: TransactionsService

    @Before
    fun setUp() {
        mockWebServer.start()
        mockWebServer.dispatcher = setUpMockWebServerDispatcher()
        setUpTransactionRetrofitService()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Assert get transactions per second remote response structure match JSON Server response`() =
        runBlocking {
            // This shouldn't have to throw an error if the Transactions Response
            // is well mapped with the server response mocked in [setUpMockWebServerDispatcher]
            val remoteTransactions = transactionService.getTransactionsPerSecond(
                chartName = chartName,
                timeSpan = timeSpan,
                rollingAverage = rollingAverage
            )

            assertEquals(
                "Transactions size does not match the one provided in resources.",
                TransactionsData.provideRemoteTransactionsFromAssets().size,
                remoteTransactions.items.size
            )
        }

    private fun setUpTransactionRetrofitService() {
        transactionService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(TransactionsService::class.java)
    }

    private fun setUpMockWebServerDispatcher(): Dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            println("BASE_URL${request.path}")
            return when (request.path) {
                "/charts/$chartName?timespan=$timeSpan&rollingAverage=$rollingAverage" -> {
                    MockResponse()
                        .setResponseCode(200)
                        .setBody(FileReaderUtil.kotlinReadFileWithNewLineFromResources("transactions_response.json"))
                }
                else -> MockResponse().setResponseCode(404)
            }
        }
    }
}