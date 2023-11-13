package com.example.destinationapp.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.destinationapp.R
import com.example.destinationapp.data.Discount
import com.example.destinationapp.data.dummyCategory
import com.example.destinationapp.data.dummyDiscount
import com.example.destinationapp.di.Injection
import com.example.destinationapp.model.OrderDestination
import com.example.destinationapp.ui.ViewModelFactory
import com.example.destinationapp.ui.common.UiState
import com.example.destinationapp.ui.components.CategoryItem
import com.example.destinationapp.ui.components.DestinationItem
import com.example.destinationapp.ui.components.DiscountItem
import com.example.destinationapp.ui.components.Search

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllDestinations()
            }
            is UiState.Success -> {
                HomeContent(
                    orderDestination = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderDestination: List<OrderDestination>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    Column {
        Banner()
        SectionText(stringResource(R.string.section_category))
        CategoryRow()
        SectionText(stringResource(R.string.section_discount))
        DiscountRow(dummyDiscount)
        SectionText(stringResource(R.string.section_destination))
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(orderDestination) { data ->
                DestinationItem(
                    image = data.destination.image,
                    title = data.destination.title,
                    price = data.destination.price,
                    description = data.destination.description,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.destination.id)
                    }
                )
            }
        }
    }

}

@Composable
fun Banner(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
        )
        Search()
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category)
        }
    }
}

@Composable
fun SectionText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun DiscountRow(
    listDiscount: List<Discount>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listDiscount, key = { it.title }) { menu ->
            DiscountItem(menu)
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(
        navigateToDetail = {}
    )
}