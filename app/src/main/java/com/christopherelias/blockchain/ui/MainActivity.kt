package com.christopherelias.blockchain.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.christopherelias.blockchain.ui.components.CurrencyStatistics
import com.christopherelias.blockchain.ui.components.StatsHorizontalList
import com.christopherelias.blockchain.ui.models.Stats
import com.christopherelias.blockchain.ui.theme.BlockchainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlockchainTheme {
                Content(viewModel = viewModel)
            }
        }
    }
}


/*
 * TODO:
 *  Load Cards in Repository
 *  Add HomeRepositoryUnitTests
 *  Display list of StatsCards Composable in a 'GridView'
 *  Make the whole screen Scrollable
 *  Implement & Display Failure Screen if some failure is thrown
 */
@Composable
fun Content(viewModel: MainViewModel) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        val homeState: HomeState by viewModel.homeState.observeAsState(HomeState())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            with(homeState) {
                // Header
                item {
                    StatsHorizontalList(
                        stats = listOf(
                            Stats(
                                "Market Price",
                                "$36,980.20",
                                "The avarage USD market price acorss major bitcoin exchanges"
                            ),
                            Stats(
                                "Market Price",
                                "$36,980.20",
                                "The avarage USD market price acorss major bitcoin exchanges"
                            )
                        )
                    )

                }
                // Body Stats
                item {
                    CurrencyStatistics(transactionsPerSecond = transactionsPerSecond)
                }
            }
        }
    }
}