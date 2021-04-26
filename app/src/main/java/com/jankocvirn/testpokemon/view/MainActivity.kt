package com.jankocvirn.testpokemon.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.jankocvirn.testpokemon.R
import com.jankocvirn.testpokemon.databinding.ActivityMainBinding
import com.jankocvirn.testpokemon.extensions.loadImage
import com.jankocvirn.testpokemon.extensions.toast
import com.jankocvirn.testpokemon.model.pokemon.Move
import com.jankocvirn.testpokemon.model.pokemon.Pokemon
import com.jankocvirn.testpokemon.model.pokemon.Stat
import com.jankocvirn.testpokemon.view.adapter.ItemAdapter
import com.jankocvirn.testpokemon.view.adapter.StatsAdapter
import com.jankocvirn.testpokemon.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.start()
    }

    private fun observeViewModel() {
        viewModel.progressObservable.observe(this, {
            it?.let {
                toggleProgress(it)
            }
        })
        viewModel.errorObservable.observe(this, {
            it?.let {
                showError(it)
            }
        })
        viewModel.pokemonObservable.observe(this, {
            it?.let {
                populateUi(it)
            }
        })
    }

    private fun showError(show: Boolean) {
        when (show) {
            true -> toast(getString(R.string.general_error))
        }
    }

    private fun toggleProgress(show: Boolean) {
        binding.progress.isVisible = show
    }

    private fun populateUi(pokemon: Pokemon) {
        binding.txtName.text = pokemon.name
        binding.imgFront.loadImage(pokemon.frontImage)
        binding.imgBack.loadImage(pokemon.backImage)
        pokemon.moves?.let {
            populateMoves(it)
        }
        pokemon.stats?.let {
            populateStats(it)
        }
    }

    private fun populateStats(list: List<Stat>) {
        binding.lblStats.isVisible = true
        val adapter = StatsAdapter()
        adapter.setData(list)
        binding.recyclerStats.adapter = adapter
    }

    private fun populateMoves(list: List<Move>) {
        binding.lblMoves.isVisible = true
        val adapter = ItemAdapter()
        adapter.setData(list)
        binding.recyclerMoves.adapter = adapter
    }

    private fun setupListeners() {
        binding.fabReload.setOnClickListener {
            viewModel.start()
        }
    }
}
