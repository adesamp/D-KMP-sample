package eu.baroncelli.dkmpsample.shared.viewmodel.screens.countrydetail

import eu.baroncelli.dkmpsample.shared.viewmodel.StateProvider

class CountryDetailStateProvider (private val stateProvider: StateProvider) {
    private val countryDetailEvents = CountryDetailEvents(stateProvider.events)
    fun getCountryDetailState(country: String): CountryDetailState {

        // the state gets initialized with "initState":
        //      WHEN this function is called for the first time
        //          OR if the "reinitWhen" condition is true
        // after initialization, the "callOnInit" code gets called
        return stateProvider.stateManager.getScreen(
            initState = { CountryDetailState(countryName = country, isLoading = true) },
            callOnInit = { countryDetailEvents.loadCountryDetailData(country) },
            reinitWhen = { country != it.countryName }
        )

    }
}