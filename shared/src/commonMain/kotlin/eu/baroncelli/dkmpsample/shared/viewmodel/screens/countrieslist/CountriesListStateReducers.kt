package eu.baroncelli.dkmpsample.shared.viewmodel.screens.countrieslist

import eu.baroncelli.dkmpsample.shared.datalayer.functions.getCountriesListData
import eu.baroncelli.dkmpsample.shared.viewmodel.StateManager
import eu.baroncelli.dkmpsample.shared.viewmodel.StateReducers


class CountriesListStateReducers(private val stateReducers: StateReducers) {
    fun restoreSelectedMenuItem(): MenuItem {
        //debugLogger.log("START restoreSelectedMenuItem")
        // restore the selected MenuItem saved in Settings into the state object
        val savedSelectedMenuItem = stateReducers.dataRepository.localSettings.selectedMenuItem
        stateReducers.stateManager.updateScreen(CountriesListState::class) {
            it.copy(selectedMenuItem = savedSelectedMenuItem)
        }
        return savedSelectedMenuItem
    }


    suspend fun updateCountriesList(menuItem: MenuItem) {
        // update CountriesListState, after retrieving the countries list from the repository
        var listData = stateReducers.dataRepository.getCountriesListData()
        if (menuItem == MenuItem.FAVORITES) {
            // in case the Favorites tab has been selected, only get the favorite countries
            listData = listData.filter {
                stateReducers.dataRepository.localSettings.favoriteCountries.containsKey(it.name)
            }
        }
        stateReducers.stateManager.updateScreen(CountriesListState::class) {
            it.copy(
                countriesListItems = listData,
                selectedMenuItem = menuItem,
                isLoading = false,
                favoriteCountries = stateReducers.dataRepository.localSettings.favoriteCountries,
            )
        }
        // save the MenuItem again into the Settings (as a "side-effect")
        stateReducers.dataRepository.localSettings.selectedMenuItem = menuItem
    }


    fun toggleFavorite(country: String) {
        // update the favorites map and save it into the state object
        val favoriteCountries = stateReducers.dataRepository.localSettings.favoriteCountries
        if (favoriteCountries.containsKey(country)) {
            favoriteCountries.remove(country)
        } else {
            favoriteCountries[country] = true
        }
        stateReducers.stateManager.updateScreen(CountriesListState::class) {
            it.copy(favoriteCountries = favoriteCountries)
        }
        // save the favoriteCountries map again into the Settings (as a "side-effect")
        stateReducers.dataRepository.localSettings.favoriteCountries = favoriteCountries
    }
}