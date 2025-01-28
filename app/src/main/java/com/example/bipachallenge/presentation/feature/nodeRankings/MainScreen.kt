package com.example.bipachallenge.presentation.feature.nodeRankings

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bipachallenge.domain.model.NodeRankingData
import com.example.bipachallenge.presentation.feature.nodeRankings.viewModel.NodeRankingsViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: NodeRankingsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val rankings by viewModel.nodeRankings.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNodeRankings()
    }
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.clearErrorMessage()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        color = Color.White,
                        text = "Node Rankings",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF242833)
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = isLoading,
            onRefresh = { viewModel.getNodeRankings() },
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF333742))
                .padding(innerPadding)
        ) {

            if (isLoading && rankings.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(rankings) { ranking ->
                        NodeRankingItem(ranking) {
                            //navController.navigate("details_screen/${ranking.publicKey}")
                        }
                        Spacer(
                            Modifier
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NodeRankingItem(
    ranking: NodeRankingData,
    onItemClick: () -> Unit
) {
    val country = ranking.country?.values?.first()
    val city = ranking.city?.values?.first()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, shape = RoundedCornerShape(10.dp))
            .background(Color(0xFF242833), shape = RoundedCornerShape(10.dp))
            .border(2.dp, Color(0xFF242833), RoundedCornerShape(10.dp))
            .padding(16.dp)
            .clickable { onItemClick() }
    ) {
        NodeAlias(ranking.alias)

        // Detalhes de ranking
        LabelValue(label = "Public Key", value = ranking.publicKey)
        LabelValue(label = "Channels", value = ranking.channels.toString())
        LabelValue(label = "Capacity", value = ranking.capacity)
        LabelValue(label = "First Seen", value = ranking.firstSeen)
        LabelValue(label = "Updated At", value = ranking.updatedAt)

        city?.let { LabelValue(label = "City", value = it) }
        country?.let { LabelValue(label = "Country", value = it) }
    }
}

@Composable
fun LabelValue(label: String, value: String?) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            color = Color.White,
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            color = Color.White,
            text = value ?: "N/A",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun NodeAlias(alias: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = Color.White,
            text = alias,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            thickness = 1.dp,
            color = Color(0xFF0A3D80)
        )
    }
}
