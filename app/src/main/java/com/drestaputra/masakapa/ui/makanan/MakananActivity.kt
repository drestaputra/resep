package com.drestaputra.masakapa.ui.makanan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.drestaputra.masakapa.databinding.ActivityMakananBinding
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.SimpleTarget
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.transition.Transition


class MakananActivity : AppCompatActivity() {
    private val viewModel: MakananViewModel by lazy {
        ViewModelProviders.of(this).get(MakananViewModel::class.java)
    }
    private var isAsc: Boolean = true
    private var _binding: ActivityMakananBinding? = null
    private lateinit var binding: ActivityMakananBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakananBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        dapatkan data dari intent category
        val categoryMasakan: String = intent.getStringExtra("CategoryString").toString()
        val categoryDescMasakan: String = intent.getStringExtra("CategoryDescString").toString()
        val categoryImgMasakan: String = intent.getStringExtra("CategoryImage").toString()
        viewModel.getMealsData(categoryMasakan)

        binding.toolbar.setNavigationOnClickListener { v ->
            finish()
        }

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.mealsGrid.adapter = MakananAdapter()


//        binding data dari intent category
        binding.TxvMasakApa.text = "Masak $categoryMasakan nih"
        binding.TxvDeskripsiCategory.text = categoryDescMasakan
        Glide.with(this).load(categoryImgMasakan)
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    binding.LlBgTopBar.setBackground(resource)
                }
            })

        binding.EtCariMakanan.addTextChangedListener { text ->
            viewModel.getMealsByName(text.toString())
            // TODO: 21/11/2021 bug scroll/reset position
            binding.mealsGrid.smoothScrollToPosition(0)
        }
        binding.ImBSorting.setOnClickListener {
            if (isAsc) {
                isAsc = false
                binding.ImBSorting.setImageResource(com.drestaputra.masakapa.R.drawable.ic_sort_up)
            } else {
                isAsc = true
                binding.ImBSorting.setImageResource(com.drestaputra.masakapa.R.drawable.ic_sort_down)
            }
            viewModel.sortMeals(isAsc)
            // TODO: 21/11/2021 bug scroll/reset position
            binding.mealsGrid.smoothScrollToPosition(0)
        }
        MakananAdapter().registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                // TODO: 21/11/2021 bug scroll/reset position
                binding.mealsGrid.smoothScrollToPosition(0)
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}