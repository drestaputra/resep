package com.drestaputra.masakapa.ui.detail_makanan

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.drestaputra.masakapa.R
import com.drestaputra.masakapa.databinding.ActivityDetailMakananBinding
import com.drestaputra.masakapa.ui.WebView
import com.drestaputra.masakapa.ui.makanan.MakananActivity
import com.drestaputra.masakapa.ui.makanan.MakananAdapter
import com.drestaputra.masakapa.ui.makanan.MakananViewModel
import com.google.android.material.snackbar.Snackbar

class DetailMakananActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMakananBinding
    private val viewModelDetail: DetailMakananViewModel by lazy {
        ViewModelProviders.of(this).get(DetailMakananViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMakananBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModelDetail = viewModelDetail
        setContentView(binding.root)

        val detailMasakanId: String = intent.getStringExtra("detailMasakanId").toString()

        val detailImgMasakan: String = intent.getStringExtra("detailImgMasakan").toString()



        Glide.with(this).load(detailImgMasakan)
            .into(object : SimpleTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    binding.LlBgTopBar.setBackground(resource)
                }
            })
        if (!detailMasakanId.equals("")){
            viewModelDetail.getMealsData(detailMasakanId)
        }else{
            finish()
            Snackbar.make(binding.root,"Detail Makanan tidak ditemukan",Snackbar.LENGTH_LONG).show()
        }


        binding.ImBYoutube.setOnClickListener {
            if (!viewModelDetail.meals.value!!.strYoutube.equals("")){
                val IYoutube = Intent(
                    this@DetailMakananActivity,
                    WebView::class.java
                )
                IYoutube.putExtra("url", viewModelDetail.meals.value!!.strYoutube)
                startActivity(IYoutube)
            }else{
                Snackbar.make(binding.root,"Video tidak ditemukan",Snackbar.LENGTH_LONG).show()
            }
        }

        binding.toolbar.setNavigationOnClickListener { v ->
            finish()
        }



    }
}