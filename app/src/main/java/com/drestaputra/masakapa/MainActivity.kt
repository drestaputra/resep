package com.drestaputra.masakapa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
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
//        agar bottom nav tidak ter adjust ketika soft keyboard muncul
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        navView.setupWithNavController(navController)
//        set agar bottom nav index ke 1 un clickable dan uncheckable, karena dialihkan fungsinya ke fab
        binding.bottomNavigationView.menu.getItem(1).isCheckable = false
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
        binding.fab.setOnClickListener { v->
            navView.selectedItemId = R.id.navigation_pencarian
            navController.navigate(R.id.navigation_search)
        }

    }

}