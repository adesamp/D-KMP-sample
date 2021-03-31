package eu.baroncelli.dkmpsample.shared.viewmodel.screens.countrydetail

import eu.baroncelli.dkmpsample.shared.viewmodel.Events

class CountryDetailEvents (private val events: Events) {
    private val countryDetailStateReducers = CountryDetailStateReducers(events.stateReducers,)

    /********** INTERNAL event function, used by the StateProvider **********/

    internal fun loadCountryDetailData(country: String) {
        // launch a coroutine, as "updateCountryDetail" is a suspend function
        events.launchCoroutine {
            countryDetailStateReducers.updateCountryDetail(country)
        }
    }
}