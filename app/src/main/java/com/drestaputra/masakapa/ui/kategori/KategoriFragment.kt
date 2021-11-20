package com.drestaputra.masakapa.ui.kategori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.drestaputra.masakapa.databinding.FragmentKategoriBinding

class KategoriFragment: Fragment() {
    private val viewModel: CategoryViewModel by lazy {
        ViewModelProviders.of(this).get(CategoryViewModel::class.java)
    }
    private var _binding: FragmentKategoriBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentKategoriBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.movieGrid.adapter = CategoryAdapter()

        viewModel.categories.observe(viewLifecycleOwner, Observer {
//            binding.viewModel = viewModel
        })
        val root: View = binding.root
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}