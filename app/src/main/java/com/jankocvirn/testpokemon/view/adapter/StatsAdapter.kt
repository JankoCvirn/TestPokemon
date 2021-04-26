package com.jankocvirn.testpokemon.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jankocvirn.testpokemon.databinding.ItemMoveBinding
import com.jankocvirn.testpokemon.model.pokemon.Stat

class StatsAdapter(
) : RecyclerView.Adapter<StatsAdapter.SeasonViewHolder>() {

    private val items = arrayListOf<Stat>()

    fun setData(list: List<Stat>) {
        val diffCallback = DiffCallback(items, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMoveBinding.inflate(inflater, parent, false)
        return SeasonViewHolder(binding)
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bind(item = items[position])
    }

    class SeasonViewHolder(val binding: ItemMoveBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Stat) {
            binding.txtMove.text = item.name
        }
    }

    class DiffCallback(private val old: List<Stat>, private val new: List<Stat>) :
        DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            old[oldItemPosition] == new[newItemPosition]

        override fun getOldListSize(): Int = old.size

        override fun getNewListSize(): Int = new.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = old[oldItemPosition]
            val new = new[newItemPosition]
            return old == new
        }
    }
}
