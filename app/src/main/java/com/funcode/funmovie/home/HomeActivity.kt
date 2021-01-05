package com.funcode.funmovie.home

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.funcode.funmovie.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        configureAppBar()
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return

        // Set up Action Bar
        val navController = host.navController
        setupBottomNavMenu(navController)
    }

    private fun configureAppBar() {
        setSupportActionBar(topAppBar)
        topAppBar.elevation = 0f
        supportActionBar?.customView?.elevation = 0f
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav?.setupWithNavController(navController)
        bottomNav?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    navController.navigate(R.id.nav_dashboard_fragment, null)
                    true
                }
                R.id.page_2 -> {
                    val uri = Uri.parse("funmovie://favorite")
                    navController.navigate(uri)
                    true
                }
                R.id.page_3 -> {
                    navController.navigate(R.id.nav_profile_fragment, null)
                    true
                }
                else -> false
            }
        }
    }
}