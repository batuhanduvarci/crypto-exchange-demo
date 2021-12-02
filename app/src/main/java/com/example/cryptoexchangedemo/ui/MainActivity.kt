package com.example.cryptoexchangedemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.cryptoexchangedemo.R
import com.example.cryptoexchangedemo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: SharedViewModel by viewModels()
        lifecycleScope.launchWhenStarted {
            viewModel.getFavoriteCoinList()
        }
        initializeBottomNavigation()
    }

    private fun initializeBottomNavigation() {
        bottomNavigation = findViewById(R.id.bottomNavigationView)
        val navigationController = (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomNavigation, navigationController)
    }
}