package com.jankocvirn.testpokemon.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jankocvirn.testpokemon.databinding.ActivityMainBinding
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
    }
}