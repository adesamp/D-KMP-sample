package eu.baroncelli.dkmpsample.android.screens.countrieslist


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.baroncelli.dkmpsample.android.screens.LoadingElement
import eu.baroncelli.dkmpsample.shared.viewmodel.screens.countrieslist.CountriesListEvents
import eu.baroncelli.dkmpsample.shared.viewmodel.screens.countrieslist.CountriesListState

@Composable
fun CountriesListScreen(countriesListState: CountriesListState, events : CountriesListEvents, onListItemClick: (String) -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "D-KMP sample", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            })
        },
        content = { paddingValues ->
            if (countriesListState.isLoading) {
                LoadingElement()
            } else {
                if (countriesListState.countriesListItems.isEmpty()) {
                    Text(text = "empty list", style = MaterialTheme.typography.body1, modifier = Modifier.padding(top=30.dp).fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 18.sp)
                } else {
                    LazyColumn(contentPadding = paddingValues) {
                        stickyHeader {
                            CountriesListHeader()
                        }
                        items(items = countriesListState.countriesListItems, itemContent = { item ->
                            CountriesListRow(
                                item = item,
                                favorite = countriesListState.favoriteCountries.containsKey(item.name),
                                onItemClick = { onListItemClick(item.name) },
                                onFavoriteIconClick = { events.selectFavorite(item.name) })
                        })
                    }
                }
            }
        },
        bottomBar = {
            CountriesListBottomBar(selectedItem = countriesListState.selectedMenuItem, onItemClick = { events.selectMenuItem(it) })
        }
    )
}