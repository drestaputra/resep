package com.drestaputra.masakapa.ui.pencarian

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
import com.drestaputra.masakapa.databinding.FragmentPencarianBinding
import kotlinx.android.synthetic.main.fragment_kategori.*

class PencarianFragment: Fragment() {
    private var isAsc: Boolean = true
    private val viewModel: PencarianViewModel by lazy {
        ViewModelProviders.of(this).get(PencarianViewModel::class.java)
    }
    private var _binding: FragmentPencarianBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentPencarianBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.pencarianGrid.adapter = PencarianAdapter()

        binding.EtCariMakanan.addTextChangedListener { text ->
            viewModel.getMealsData(text.toString())
            // TODO: 21/11/2021 bug scroll/reset position
            binding.pencarianGrid.smoothScrollToPosition(0)
        }

        binding.ImBSorting.setOnClickListener { v->
            if (isAsc){
                isAsc = false
                binding.ImBSorting.setImageResource(R.drawable.ic_sort_up)
            }else{
                isAsc = true
                binding.ImBSorting.setImageResource(R.drawable.ic_sort_down)
            }
            viewModel.sortMeals(isAsc)
            // TODO: 21/11/2021 bug scroll/reset position
            binding.pencarianGrid.smoothScrollToPosition(0)
        }
        PencarianAdapter().registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                // TODO: 21/11/2021 bug scroll/reset position
                binding.pencarianGrid.smoothScrollToPosition(0)
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