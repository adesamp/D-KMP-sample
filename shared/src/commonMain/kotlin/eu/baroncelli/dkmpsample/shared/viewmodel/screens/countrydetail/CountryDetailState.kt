package eu.baroncelli.dkmpsample.shared.viewmodel.screens.countrydetail

import eu.baroncelli.dkmpsample.shared.datalayer.sources.webservices.apis.CountryExtraData
import eu.baroncelli.dkmpsample.shared.datalayer.sources.webservices.apis.CountryListData
import eu.baroncelli.dkmpsample.shared.viewmodel.ScreenState
import eu.baroncelli.dkmpsample.shared.viewmodel.utils.toCommaThousandString
import eu.baroncelli.dkmpsample.shared.viewmodel.utils.toPercentageString

/********** ScreenState class definition **********/

data class CountryDetailState (
    val isLoading: Boolean = false,
    val countryName : String = "",
    val countryInfo : CountryInfo = CountryInfo(),
): ScreenState

/********** property classes definition **********/

class CountryInfo (
    _listData : CountryListData = CountryListData(),
    _extraData : CountryExtraData? = CountryExtraData(),
) {
    // in the ViewModel classes, our computed properties only do UI-formatting operations
    // (the arithmetical operations, such as calculating a percentage, should happen in the DataLayer classes)
    val population = _listData.population.toCommaThousandString()
    val firstDoses = _listData.firstDoses.toCommaThousandString()
    val firstDosesPerc = _listData.firstDosesPercentageFloat.toPercentageString()
    val fullyVaccinated = _listData.fullyVaccinated.toCommaThousandString()
    val fullyVaccinatedPerc = _listData.fullyVaccinatedPercentageFloat.toPercentageString()
    val vaccinesList : List<String>? = _extraData?.vaccinesList
}
