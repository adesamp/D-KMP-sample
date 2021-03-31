package eu.baroncelli.dkmpsample.shared.viewmodel.screens.countrieslist

import eu.baroncelli.dkmpsample.shared.viewmodel.StateProvider

class CountriesListStateProvider(private val stateProvider: StateProvider) {
    private val countriesListEvents = CountriesListEvents(stateProvider.events)
    fun getCountriesListState(): CountriesListState {

        // the state gets initialized with "initState":
        //      ONLY WHEN this function is called for the first time
        // after initialization, the "callOnInit" code gets called
        return stateProvider.stateManager.getScreen(
            initState = { CountriesListState(isLoading = true) },
            callOnInit = { countriesListEvents.loadCountriesListData() }
        )

    }
}