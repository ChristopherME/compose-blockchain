package com.christopherelias.blockchain.core.network

import com.christopherelias.blockchain.core.network.middleware.NetworkMiddlewareFailure
import com.christopherelias.blockchain.core.network.models.ResponseError
import com.christopherelias.blockchain.core.network.utils.ServiceBodyFailure
import com.christopherelias.blockchain.core.network.utils.call
import com.christopherelias.blockchain.functional_programming.Either
import com.christopherelias.blockchain.functional_programming.Failure
import com.christopherelias.blockchain.functional_programming.utils.toError
import com.christopherelias.blockchain.functional_programming.utils.toSuccess
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/*
 * Created by Christopher Elias on 11/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@ExperimentalCoroutinesApi
class NetworkCallExtensionTest {

    private val dispatcher = TestCoroutineDispatcher()
    private val moshi = Moshi.Builder().build()
    private val adapter: JsonAdapter<ResponseError> = moshi.adapter(ResponseError::class.java)

    @Test
    fun `when lambda returns successfully then it should emit the result as success`() {
        runBlockingTest {

            val lambdaResult = true
            val result = call(ioDispatcher = dispatcher, adapter = adapter) { lambdaResult }

            assertEquals(
                lambdaResult.toSuccess(),
                result
            )
        }
    }

    @Test
    fun `when lambda throws IOException then it should emit the result as UnknownFailure`() {
        runBlockingTest {

            val result = call(
                ioDispatcher = dispatcher,
                adapter = adapter
            ) { throw IOException("The fuck happened") }

            assertEquals(
                Failure.UnexpectedFailure(message = "The fuck happened").toError(),
                result
            )
        }
    }

    @Test
    fun `when lambda throws HttpException then it should emit the result as GenericError`() {
        val errorBody = "{\"status\": \"Invalid Request\",\"error\": \"Error\"}"
            .toResponseBody("application/json".toMediaTypeOrNull())

        runBlockingTest {
            val result = call(ioDispatcher = dispatcher, adapter = adapter) {
                throw HttpException(Response.error<Any>(400, errorBody))
            }
            assertEquals(
                ServiceBodyFailure(
                    internalStatus = "Invalid Request",
                    internalMessage = "Error"
                ).toError(),
                result
            )
        }
    }

    @Test
    fun `when lambda throws unknown exception then it should emit UnknownFailure`() {
        runBlockingTest {
            val result = call(ioDispatcher = dispatcher, adapter = adapter) {
                throw IllegalStateException("")
            }
            assertEquals(
                Failure.UnexpectedFailure("").toError(),
                result
            )
        }
    }

    @Test
    fun `when middleware is not valid return its failure`() {
        runBlockingTest {
            val dumbMiddleware = DumbMiddleware(
                hardCodedValidation = false,
                middlewareFailureMessage = "X"
            )
            val middlewares = listOf(
                AnotherDumbMiddleware(),
                AnotherDumbMiddleware(),
                dumbMiddleware,
                AnotherDumbMiddleware()
            )
            val result = call(
                middleWares = middlewares,
                ioDispatcher = dispatcher,
                adapter = adapter
            ) {
                10
            }
            println("dumMiddleware: ${dumbMiddleware.failure.middleWareExceptionMessage}")
            println("result: $result")

            with(result as Either.Error<NetworkMiddlewareFailure>) {
                assertEquals(
                    dumbMiddleware.failure.middleWareExceptionMessage,
                    this.error.middleWareExceptionMessage
                )
            }
        }
    }

}