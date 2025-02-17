package eu.baroncelli.dkmpsample.shared.viewmodel

import eu.baroncelli.dkmpsample.shared.DebugLogger
import kotlinx.coroutines.flow.StateFlow

val debugLogger by lazy { DebugLogger("D-KMP-SAMPLE") }

class KMPViewModel {

    val stateFlow: StateFlow<AppState>
        get() = stateManager.mutableStateFlow

    internal val stateManager by lazy { StateManager() }

    internal val stateReducers by lazy { StateReducers(stateManager) }

    val events by lazy { Events(stateReducers) }

    internal val stateProvider by lazy { StateProvider(stateManager, events) }

}