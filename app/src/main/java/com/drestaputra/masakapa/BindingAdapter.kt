package com.drestaputra.masakapa

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.drestaputra.masakapa.model.Category
import com.drestaputra.masakapa.model.Meal
import com.drestaputra.masakapa.ui.kategori.CategoryAdapter
import com.drestaputra.masakapa.ui.makanan.MakananAdapter

//import com.example.mvvm.model.Category

val BASEURL_IMAGE = "https://image.tmdb.org/t/p/w185/"


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Category>?) {
    val adapter = recyclerView.adapter as CategoryAdapter
    adapter.submitList(data)
}
@BindingAdapter("listDataMeal")
fun bindRecyclerViewMeal(recyclerView: RecyclerView, data: List<Meal>?) {
    val adapter = recyclerView.adapter as MakananAdapter
    adapter.submitList(data)
}


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(it)
            .apply(
                RequestOptions()
                    .placeholder(R.color.gray_200))
            .into(imgView)
    }
}

@BindingAdapter("textTitle")
fun bindText(textView: TextView, text: String?) {
    text?.let {
        textView.text = it
    }
}