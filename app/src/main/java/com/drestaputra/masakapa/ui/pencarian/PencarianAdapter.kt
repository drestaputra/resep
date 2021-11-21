package com.drestaputra.masakapa.ui.pencarian

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drestaputra.masakapa.databinding.GridViewItemMealBinding
import com.drestaputra.masakapa.model.Meal
import com.drestaputra.masakapa.ui.detail_makanan.DetailMakananActivity

class PencarianAdapter : ListAdapter<Meal, PencarianAdapter.ViewHolder>(DiffCalback) {
    class ViewHolder(private var binding: GridViewItemMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(meal: Meal) {
            binding.meal = meal
            binding.executePendingBindings()
            binding.CvItemMeal.setOnClickListener { view ->
                val Idetail = Intent(
                    view.context,
                    DetailMakananActivity::class.java
                )


                Idetail.putExtra("detailMasakanId", meal.idMeal)
                Idetail.putExtra("detailNameMasakan", meal.strMeal)
                Idetail.putExtra("detailImgMasakan", meal.strMealThumb)
                Idetail.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                view.context.startActivity(Idetail)
            }
        }
    }

    companion object DiffCalback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(GridViewItemMealBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pencarian = getItem(position)
        holder.bind(pencarian)
    }
}