package com.drestaputra.masakapa

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.drestaputra.masakapa.data.model.Category
import com.drestaputra.masakapa.ui.kategori.CategoryAdapter

//import com.example.mvvm.model.Category

val BASEURL_IMAGE = "https://image.tmdb.org/t/p/w185/"


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Category>?) {
    val adapter = recyclerView.adapter as CategoryAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(BASEURL_IMAGE + it)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background))
            .into(imgView)
    }
}

@BindingAdapter("textTitle")
fun bindText(textView: TextView, text: String?) {
    text?.let {
        textView.text = it
    }
}