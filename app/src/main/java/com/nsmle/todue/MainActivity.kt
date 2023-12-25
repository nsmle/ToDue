package com.nsmle.todue

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nsmle.todue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide();
        }

        val navView: BottomNavigationView = binding.navView
        val navFragmentHost = R.id.nav_host_fragment_activity_main

        val appBar = setOf(
            R.id.navigation_home,
            R.id.navigation_task,
            R.id.navigation_notifications
        )

        val navController = findNavController(navFragmentHost)
        val appBarConfiguration = AppBarConfiguration(appBar)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}