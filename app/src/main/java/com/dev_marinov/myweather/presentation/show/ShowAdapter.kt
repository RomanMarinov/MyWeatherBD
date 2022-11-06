package com.dev_marinov.myweather.presentation.show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.myweather.databinding.ItemTownsBinding

class ShowAdapter(
    private val onClick: (id: String) -> Unit
) :
    ListAdapter<String, ShowAdapter.ViewHolder>(DetailDiffUtilCallback()) {

    private var towns: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemTownsBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(towns[position])
    }

    override fun submitList(list: List<String>?) {
        super.submitList(list)
        list?.let { this.towns = it.toList() }
    }

    override fun getItemCount(): Int {
        return towns.size
    }

    inner class ViewHolder(private val binding: ItemTownsBinding, onClick: (id: String) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(town: String?) {
            binding.btCity.text = town

            binding.btCity.setOnClickListener {
                onClick(town.toString())
            }
        }
    }
}

class DetailDiffUtilCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean {
        return oldItem == newItem
    }

}
