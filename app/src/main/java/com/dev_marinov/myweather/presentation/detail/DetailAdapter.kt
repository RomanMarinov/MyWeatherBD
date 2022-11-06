package com.dev_marinov.myweather.presentation.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev_marinov.myweather.R
import com.dev_marinov.myweather.databinding.ItemDetailBinding
import com.dev_marinov.myweather.domain.Detail
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class DetailAdapter :
    ListAdapter<Detail, DetailAdapter.ViewHolder>(DetailDiffUtilCallback()) {

    private var detail: List<Detail> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ItemDetailBinding.inflate(inflater, parent, false)
        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(detail[position])
    }

    override fun submitList(list: List<Detail>?) {
        super.submitList(list)
        list?.let { this.detail = it.toList() }
    }

    override fun getItemCount(): Int {
        return detail.size
    }

    inner class ViewHolder(private val binding: ItemDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detail: Detail) {
            binding.tvTime.text = detail.time
            binding.tvTempc.text = detail.temp_c
            binding.tvConditionText.text = detail.condition.text

            binding.tvWindKph.text = detail.wind_kph
            binding.tvHumidity.text = detail.humidity
            binding.tvCloud.text = detail.cloud

            val img = detail.condition.icon

            Picasso.get()  // установка главной картинки игры
                .load("https:" + img).memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(binding.imgConditionIcon) // -----> картинка

            animation(binding)
        }
    }

    fun animation(binding: ItemDetailBinding) {
        binding.tvTime.animation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1)
        binding.tvTempc.animation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1)
        binding.tvConditionText.animation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1)
        binding.tvWindKph.animation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1)
        binding.tvHumidity.animation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1)
        binding.tvCloud.animation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1)
        binding.imgConditionIcon.animation =
            AnimationUtils.loadAnimation(binding.root.context, R.anim.scale_up_1)
    }
}

class DetailDiffUtilCallback : DiffUtil.ItemCallback<Detail>() {
    override fun areItemsTheSame(oldItem: Detail, newItem: Detail): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Detail,
        newItem: Detail
    ): Boolean {
        return oldItem == newItem
    }
}
