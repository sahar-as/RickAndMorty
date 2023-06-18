package ir.saharapps.rickandmorty.ui.screen.utility

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<T>.updateState(newState: T.() -> T) {
    value = newState(value)
}