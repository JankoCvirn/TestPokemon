package com.jankocvirn.testpokemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jankocvirn.testpokemon.model.pokemon.Pokemon
import com.jankocvirn.testpokemon.repository.ApiRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val repository: ApiRepository
) : BaseViewModel() {

    private val _pokemonObservable = MutableLiveData<Pokemon>()
    val pokemonObservable: LiveData<Pokemon> = _pokemonObservable

    private val _progressObservable = MutableLiveData<Boolean>()
    val progressObservable: LiveData<Boolean> = _progressObservable

    private val _errorObservable = MutableLiveData<Boolean>()
    val errorObservable: LiveData<Boolean> = _errorObservable

    private val handler = CoroutineExceptionHandler { _, _ ->
        _errorObservable.postValue(true)
    }

    fun start() {
        _progressObservable.value = true
        scope.launch(handler) {
            val pokemon = repository.getPokemon(randomPokemonId())
            withContext(Dispatchers.Main) {
                if (pokemon != null) {
                    _pokemonObservable.value = pokemon
                } else {
                    _errorObservable.value = true
                }
            }
        }
    }

    private fun randomPokemonId() = (1..100).random()
}
