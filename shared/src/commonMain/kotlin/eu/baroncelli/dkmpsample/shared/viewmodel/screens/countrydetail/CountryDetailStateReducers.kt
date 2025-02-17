package eu.baroncelli.dkmpsample.shared.viewmodel.screens.countrydetail

import eu.baroncelli.dkmpsample.shared.datalayer.functions.getCountryInfo
import eu.baroncelli.dkmpsample.shared.viewmodel.StateManager
import eu.baroncelli.dkmpsample.shared.viewmodel.StateReducers

class CountryDetailStateReducers(private val stateReducers: StateReducers) {
    suspend fun updateCountryDetail(country: String) {
        // update CountryDetailState, after retrieving it from the Repository
        val listItemData = stateReducers.dataRepository.getCountryInfo(country)
        stateReducers.stateManager.updateScreen(CountryDetailState::class) {
            it.copy(countryInfo = listItemData, isLoading = false)
        }
    }
}