package com.drestaputra.masakapa.ui.kategori

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.drestaputra.masakapa.model.Category
import com.drestaputra.masakapa.databinding.GridViewItemBinding
import com.drestaputra.masakapa.ui.makanan.MakananActivity


class CategoryAdapter : ListAdapter<Category, CategoryAdapter.ViewHolder>(DiffCalback) {
    class ViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.category = category
            binding.executePendingBindings()
            binding.CvItemCategory.setOnClickListener { view ->
                val Idetail = Intent(
                    view.context,
                    MakananActivity::class.java
                )
                Idetail.putExtra("CategoryString", category.strCategory)
                Idetail.putExtra("CategoryDescString", category.strCategoryDescription)
                Idetail.putExtra("CategoryImage", category.strCategoryThumb)
                Idetail.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                view.context.startActivity(Idetail)
            }
        }
    }

    companion object DiffCalback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }
}