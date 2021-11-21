package com.drestaputra.masakapa.ui.kategori

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.drestaputra.masakapa.R
import com.drestaputra.masakapa.databinding.FragmentKategoriBinding
import kotlinx.android.synthetic.main.fragment_kategori.*

class KategoriFragment: Fragment() {
    private var isAsc: Boolean = true
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
        binding.kategoriGrid.adapter = CategoryAdapter()

        binding.EtCariKategori.addTextChangedListener { text ->
            viewModel.getCategoriesByName(text.toString())
            // TODO: 21/11/2021 bug scroll/reset position
            binding.kategoriGrid.smoothScrollToPosition(0)
        }

        binding.ImBSorting.setOnClickListener { v->
            if (isAsc){
                isAsc = false
                binding.ImBSorting.setImageResource(R.drawable.ic_sort_up)
            }else{
                isAsc = true
                binding.ImBSorting.setImageResource(R.drawable.ic_sort_down)
            }
            viewModel.sortCategories(isAsc)
            // TODO: 21/11/2021 bug scroll/reset position
            binding.kategoriGrid.smoothScrollToPosition(0)
        }
        CategoryAdapter().registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                // TODO: 21/11/2021 bug scroll/reset position
                binding.kategoriGrid.smoothScrollToPosition(0)
            }
        })

        val root: View = binding.root
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}