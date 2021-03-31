package eu.baroncelli.dkmpsample.shared.viewmodel.screens.countrieslist

import eu.baroncelli.dkmpsample.shared.viewmodel.Events

class CountriesListEvents (private val events: Events) {
    private val countryListStateReducers = CountriesListStateReducers(events.stateReducers)

    /********** INTERNAL event function, used by the StateProvider **********/

    internal fun loadCountriesListData() {
        val restoredSelectedMenuItem = countryListStateReducers.restoreSelectedMenuItem()
        //debugLogger.log("restoredSelectedMenuItem: "+restoredSelectedMenuItem)
        // launch a coroutine, as "updateCountriesList" is a suspend function
        events.launchCoroutine {
            countryListStateReducers.updateCountriesList(restoredSelectedMenuItem)
        }
    }


    /********** PUBLIC event functions **********/

    fun selectMenuItem(menuItem: MenuItem) {
        // launch a coroutine, as "updateCountriesList" is a suspend function
        events.launchCoroutine {
            countryListStateReducers.updateCountriesList(menuItem)
        }
    }

    fun selectFavorite(country: String) {
        countryListStateReducers.toggleFavorite(country)
    }
}