package com.drestaputra.masakapa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.drestaputra.masakapa.data.api.APIList
import com.drestaputra.masakapa.data.api.RetrofitClient
import com.drestaputra.masakapa.databinding.ActivityMainBinding
import com.drestaputra.masakapa.ui.kategori.CategoryViewModel
import com.drestaputra.masakapa.ui.kategori.KategoriFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CategoryViewModel
    private lateinit var binding: ActivityMainBinding
    val fragmentKategori: Fragment = KategoriFragment()
    val fm = supportFragmentManager
    var active = fragmentKategori
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setupWithNavController(navController)
//        set agar bottom nav index ke 1 un clickable dan uncheckable, karena dialihkan fungsinya ke fab
        binding.bottomNavigationView.menu.getItem(1).isCheckable = false
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false

    }

}