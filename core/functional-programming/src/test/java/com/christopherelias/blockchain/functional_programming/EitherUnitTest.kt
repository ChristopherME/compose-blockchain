package com.christopherelias.blockchain.functional_programming

import com.christopherelias.blockchain.functional_programming.utils.toError
import com.christopherelias.blockchain.functional_programming.utils.toSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

/*
 * Created by Christopher Elias on 11/06/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

@ExperimentalCoroutinesApi
class EitherUnitTest {

    @Test
    fun `assert coMapSuccess method will return a Success struct and not a Failure`() {
        runBlockingTest {
            val myAge = getAgeService().coMapSuccess { age -> age + 1 }
            Assert.assertTrue("My Age is not success.", myAge.isSuccess)

            myAge.coMapSuccess { resultAge ->
                Assert.assertEquals(26, resultAge)
            }

        }
    }

    @Test
    fun `assert coMapSuccess method will pass the failure if is not a Success`() {
        runBlockingTest {
            val someAge = someFailure().coMapSuccess { ageIfSuccess -> ageIfSuccess + 1 }
            Assert.assertTrue("My age is a success after all", someAge.isError)
            Assert.assertFalse(someAge.isSuccess)

            with(someAge as Either.Error<Failure>) {
                with(this.error as Failure.UnexpectedFailure) {
                    assertEquals(message, "ups!")
                }
            }
        }
    }

    private fun getAgeService(): Either<Failure, Int> =
        (2021.minus(1996)).toSuccess()

    private fun someFailure(): Either<Failure, Int> =
        Failure.UnexpectedFailure("ups!").toError()
}